/*
 * Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
 * All rights reserved.
 * This component and the accompanying materials are made available
 * under the terms of the License "Symbian Foundation License v1.0"
 * which accompanies this distribution, and is available
 * at the URL "http://www.symbianfoundation.org/legal/sfl-v10.html".
 *
 * Initial Contributors:
 * Nokia Corporation - initial contribution.
 *
 * Contributors:
 *
 * Description:
 *
 */
package com.nokia.testfw.launch.monitor;

import junit.framework.TestCase;

/**
 * @author xiaoma
 * 
 */
public class TailFileInputStreamTest extends TestCase {

	private static final String TESTFILE = "tailstream.test";

	// private File testFile;

	// during setup, we will start a thread to write to disk file
	protected void setUp() throws Exception {
		// testFile = new File(TESTFILE);
		LogFileWriter logWriter;
		// write log every second in separate thread, last for 10 seconds
		logWriter = new LogFileWriter(TESTFILE, 10);
		Thread writerThread = new Thread(logWriter);
		writerThread.start();
	}

	protected void tearDown() throws Exception {
		// testFile.delete();
	}

	public void testTailFile() {
		// use tail stream to read the file while other app is writing.
		TailFileInputStream tis;
		boolean finished = false;
		StringBuffer sb = new StringBuffer();
		try {
			tis = new TailFileInputStream(TESTFILE);
			byte[] bytes = new byte[10];
			System.out.println("begin to read log file");
			while (!finished) {
				if (tis.available() > 0) {
					int count = 0;
					while ((count = tis.read(bytes)) != -1) {
						String line = new String(bytes, 0, count).trim();
						System.out.println("read log :" + line);
						sb.append(line);
						if (line.equalsIgnoreCase("test10")) {
							finished = true;
							break;
						}
					}
					Thread.sleep(100);
				}
			}
			tis.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}

		String content = sb.toString();
		System.out.println("log file content:" + content);
		assertTrue(content.indexOf("oldtest") < 0);
		assertTrue(content.indexOf("test4") > 0);
		assertTrue(content.indexOf("test9") > 0);
	}

}
