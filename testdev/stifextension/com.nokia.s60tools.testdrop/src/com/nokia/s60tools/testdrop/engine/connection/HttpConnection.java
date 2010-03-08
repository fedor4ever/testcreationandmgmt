/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of "Eclipse Public License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.eclipse.org/legal/epl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:
*
*/


package com.nokia.s60tools.testdrop.engine.connection;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CancellationException;

import org.eclipse.core.runtime.IProgressMonitor;

import com.nokia.s60tools.testdrop.engine.job.SendDropJob;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;

/**
 * Provides all needed http connections. 
 * Takes care of sending test drop to ATS Server
 * 
 */
public class HttpConnection {

	private final String NEWLINE = "\r\n";
	private final String PREFIX = "--";
	private final String BOUNDARY = "AaB03x";
	private final String FILE_EXTENSION_MARK = "."; 
	private final String EXPECTED_RESPONSE_IN_CONNECTION_TEST = "testrunpath or testrunid is missing!\n";
	private final String EXPECTED_RESULT_IN_CONNECTION_TEST = Messages
			.getString("HttpConnection.connectionIsOkException"); 
	private final String CONNECTION_FAILED = Messages
			.getString("HttpConnection.failedToConnectToHostException"); 
	private final String TEST_RUN_ID = "TEST_RUN_REF_ID="; 
	private final String NEXT_LINE = "\n"; 
	private final int WAITING_TIME = 10000;
	private final String TEST_DROP_FILE_NAME = "testDrop.zip"; 

	private final String RUN_FINISHED = "Finished"; 
	private final String RUN_FAILED = "Failed"; 
	private final String RUN_ABORTED = "Aborted"; 
	private final String RUN_ABORTING = "Aborting"; 

	private final String STATUS_START_TAG = "<tr><td><b>Status:&nbsp;</b></td><td>"; 
	private final String STATUS_END_TAG = "</td></tr>"; 

	public static final String POST_METHOD = "POST"; 
	public static final String GET_METHOD = "GET"; 

	private final String host;
	private final int port;
	private final String username;
	private final String password;
	private final File testpath;
	private final String schedule;

	/**
	 * Constructs class with mandatory parameter
	 * 
	 * @param host
	 *            host name
	 * @param port
	 *            server port
	 * @param username
	 *            user name
	 * @param password
	 *            user's password
	 * @param testpath
	 *            test drop source path
	 * @param schedule
	 *            schedule parameter for make a scheduled run
	 */
	public HttpConnection(String host, int port, String username,
			String password, File testpath, String schedule) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.testpath = testpath;
		this.schedule = schedule;

	}

	/**
	 * Send test drop by get method
	 * 
	 * @return response from the ATS server
	 * 
	 * @throws IOException
	 *             if sending fails
	 */
	private String sendTestDropGet() throws IOException {
		String response = null;
		HttpURLConnection conn = null;
		try {
			String testPathWithoutSpaces = testpath.toString().replaceAll(" ", "%20");
			URL url = new URL("http://" + host + ":" + port  
					+ "/ats3/XTestRunExecute.do?username=" + username 
					+ "&password=" + password + "&testrunpath="  
					+ testPathWithoutSpaces + "&schedule" + schedule); 
			conn = (HttpURLConnection) url.openConnection();
			// gets result from server
			response = getResponse(conn);
			return response;
		} catch (MalformedURLException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (IOException e) {
			throw new IOException(
					Messages
							.getString("HttpConnection.cannotConnectToATSServerException") + host + ")");  
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return response;
	}

	/**
	 * Send test drop by post method
	 * 
	 * @return response from the ATS server
	 * 
	 * @throws IOException
	 *             if sending fails
	 */
	private String sendTestDropPost() throws IOException {
		String response = null;
		HttpURLConnection conn = null;
		try {
			// initialize connection
			URL url = new URL("http://" + host + ":" + port  
					+ "/ats3/XTestRunExecute.do?username=" + username 
					+ "&password=" + password); 
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); 
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", 
					"multipart/form-data; boundary=AaB03x"); 

			// start
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(PREFIX);
			out.writeBytes(BOUNDARY);
			out.writeBytes(NEWLINE);

			// write content header
			String name = TEST_DROP_FILE_NAME.substring(0, TEST_DROP_FILE_NAME
					.indexOf(FILE_EXTENSION_MARK));
			String mimeType = "multipart/form-data; boundary=AaB03x"; 
			out.writeBytes("Content-Disposition: form-data; name=\"" + name 
					+ "\"; filename=\"" + TEST_DROP_FILE_NAME + "\"");  
			out.writeBytes(NEWLINE);
			if (mimeType != null) {
				out.writeBytes("Content-Type: " + mimeType); 
				out.writeBytes(NEWLINE);
			}
			out.writeBytes(NEWLINE);

			// content
			FileInputStream fis = new FileInputStream(testpath);

			byte[] content = new byte[1024];
			int numberOfBytesRead = 0;
			while ((numberOfBytesRead = fis.read(content, 0, content.length)) != -1) {
				out.write(content, 0, numberOfBytesRead);
			}
			// closes file input stream
			try {
				fis.close();
			} catch (Exception e) {
				LogExceptionHandler.log(e.getMessage());
			}
			out.writeBytes(NEWLINE);
			out.flush();

			// end
			out.writeBytes(PREFIX);
			out.writeBytes(BOUNDARY);
			out.writeBytes(PREFIX);
			out.writeBytes(NEWLINE);
			out.flush();
			out.close();

			// gets results from server
			response = getResponse(conn);
			return response;

		} catch (MalformedURLException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (IOException e) {
			throw new IOException(
					Messages
							.getString("HttpConnection.cannotConnectToATSServer") + host 
							+ ")"); 
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return response;
	}

	/**
	 * Gets response message from the ATS Server
	 * 
	 * @param conn
	 *            HttpURLConnection connection
	 * @return response message got from the ATS Server
	 * @throws ConnectException
	 *             if cannot get input stream from the ATS Server
	 * @throws Exception
	 *             if ATS Server responses by error message
	 */
	private String getResponse(HttpURLConnection conn) throws ConnectException,
			Exception {
		if (conn == null) {
			throw new IllegalArgumentException(Messages
					.getString("HttpConnection.connectionCannotBeNull")); 
		}
		boolean gotError = false;
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new InputStreamReader(conn
					.getInputStream()));
		} catch (Exception e) {
			LogExceptionHandler.log(e.getMessage());
			gotError = true;
		}
		if (gotError) {
			try {
				bufReader = new BufferedReader(new InputStreamReader(conn
						.getErrorStream()));
			} catch (Exception ex) {
				throw new ConnectException(CONNECTION_FAILED + " (" + host 
						+ ")"); 
			}
		}
		StringBuffer buffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufReader.readLine()) != null) {
			buffer.append(inputLine);
			buffer.append("\n"); 
		}
		if (gotError) {
			LogExceptionHandler.log(buffer.toString());
			throw new Exception(buffer.toString());
		}
		return buffer.toString();
	}

	/**
	 * Resolves executing state at ATS Server
	 * 
	 * @param response
	 *            response message from the ATS Server
	 * @param progressMonitor
	 *            progress monitor instance
	 * @return true if executing is done otherwise false
	 * @throws Exception
	 *             if session has expired
	 * @throws InterruptedException if test run is aborted from the ATS Server
	 */
	private boolean isFinished(String response, IProgressMonitor progressMonitor)
			throws Exception, InterruptedException {
		try {
			String status = response.substring(response
					.indexOf(STATUS_START_TAG));
			status = status.substring(STATUS_START_TAG.length(), status
					.indexOf(STATUS_END_TAG));
			if (progressMonitor != null) {
				progressMonitor.setTaskName(SendDropJob.WATING_FOR_RESULTS
						+ " ( Status: " + status + "... )");  
			}
			if (status.indexOf(RUN_ABORTED) != -1
					|| status.indexOf(RUN_ABORTING) != -1) {
				throw new InterruptedException(
						Messages
								.getString("HttpConnection.testRunIsAbortedFromATSServerException")); 
			}
			if (status.indexOf(RUN_FINISHED) != -1
					|| status.indexOf(RUN_FAILED) != -1) {
				return true;
			} else {
				return false;
			}
		} catch (StringIndexOutOfBoundsException ex) {
			throw new Exception(Messages
					.getString("HttpConnection.sessionHasExpiredException")); 
		}
	}

	/**
	 * Sends test drop to the ATS Server
	 * 
	 * @param method
	 *            it can be either GET or POST method
	 * @return Test run ID
	 * @throws IOException
	 *             if connection to server refused
	 */
	public int sendTestDrop(String method) throws IOException {
		int id = -1;
		String response = null;
		if (method == null) {
			throw new IllegalArgumentException(Messages
					.getString("HttpConnection.methodCannotBeNullException")); 
		}
		if (method.equals(GET_METHOD)) {
			response = sendTestDropGet();
		} else if (method.equals(POST_METHOD)) {
			response = sendTestDropPost();
		} else {
			throw new IllegalArgumentException("Method can be either " 
					+ GET_METHOD + " or " + POST_METHOD + ".");  
		}
		if (response != null) {
			id = Integer.valueOf(response.substring(TEST_RUN_ID.length(),
					response.indexOf(NEXT_LINE)));
		}
		return id;
	}

	/**
	 * Returns devices retrieved from the ATS Server
	 * 
	 * @return XML content from the server
	 * @throws IOException
	 *             if cannot get target device's information from the ATS Server
	 */
	public InputStream getTargetDevicesFromServer() throws IOException {
		try {
			URL url = new URL("http://" + host + ":" + port  
					+ "/ats3/Environment.do?action=getenvironment&username=" 
					+ username + "&password=" + password); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// gets result from server
			String response = getResponse(conn);
			return new ByteArrayInputStream(response.getBytes());
		} catch (IOException ex) {
			throw new IOException(
					Messages
							.getString("HttpConnection.cannotConnectToATSServer") + host 
							+ ")"); 
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

	}

	/**
	 * Tests connection to the ATS Server
	 * 
	 * @return Response message from the ATS Server
	 * 
	 * @throws IOException
	 *             if sending fails
	 */
	public String testConnection() throws IOException {
		String response = null;
		try {
			URL url = new URL("http://" + host + ":" + port  
					+ "/ats3/XTestRunExecute.do?username=" + username 
					+ "&password=" + password); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// gets result from server
			response = getResponse(conn);
		} catch (ConnectException ce) {
			throw new IOException(ce.getMessage());
		} catch (MalformedURLException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (IOException e) {
			throw new IOException(
					Messages
							.getString("HttpConnection.cannotConnectToATSServer") + host 
							+ ")"); 
		} catch (Exception e) {
			response = e.getMessage();
			if (response.equals(EXPECTED_RESPONSE_IN_CONNECTION_TEST)) {
				response = EXPECTED_RESULT_IN_CONNECTION_TEST;
			}
		}
		return response;
	}

	/**
	 * Gets result from the ATS Server
	 * 
	 * @param testrun
	 *            test run id
	 * @param reportType
	 *            reporting type
	 * @param progressMonitor
	 *            IProgressMonitor for getting canceling operation
	 * @return result
	 * @throws IOException
	 *             if something goes wrong with getting result
	 */
	public String getResultFromServer(int testrun, String reportType,
			IProgressMonitor progressMonitor) throws IOException {
		String response = null;
		HttpURLConnection conn = null;
		try {
			if (CookieHandler.getDefault() == null) {
				CookieHandler.setDefault(new SessionHandler());
			}
			URL urlLogon = new URL("http://" + host + ":" + port  
					+ "/ats3/Logon.do"); 
			conn = (HttpURLConnection) urlLogon.openConnection();

			conn.setRequestMethod(POST_METHOD);
			conn.setDoOutput(true);
			conn.setUseCaches(false);

			conn.setRequestProperty("Content-Type", 
					"application/x-www-form-urlencoded"); 

			OutputStreamWriter out = new OutputStreamWriter(conn
					.getOutputStream());
			String params = "username=" + username + "&password=" + password  
					+ "&selSessionTimeout=-1&lang=en"; 

			out.write(params);
			out.flush();
			conn.getContent();
			response = getResponse(conn);

			URL urlResults = new URL("http://" + host + ":" + port  
					+ "/ats3/HTMLReport.do?testrun=" + testrun + "&reportType="  
					+ reportType);
			response = null;

			boolean progressMonitorEnabled = false;
			if (progressMonitor != null) {
				progressMonitorEnabled = true;
			}

			while (true) {
				Thread.sleep(WAITING_TIME);
				if (progressMonitorEnabled) {
					LogExceptionHandler.cancelIfNeed(progressMonitor);
				}
				conn = (HttpURLConnection) urlResults.openConnection();
				conn.getContent();
				response = getResponse(conn);

				if (isFinished(response, progressMonitor)) {
					break;
				}
			}

		} catch (ConnectException ce) {
			throw new IOException(ce.getMessage());
		} catch (MalformedURLException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (CancellationException ce) {
			throw new IOException(ce.getMessage());
		} catch (IOException e) {

			throw new IOException(
					Messages
							.getString("HttpConnection.cannotConnectToATSServer") + host 
							+ ")"); 
		} catch (InterruptedException e) {
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			response = e.getMessage();
			if (response.equals(EXPECTED_RESPONSE_IN_CONNECTION_TEST)) {
				response = EXPECTED_RESULT_IN_CONNECTION_TEST;
			}
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return response;
	}
}
