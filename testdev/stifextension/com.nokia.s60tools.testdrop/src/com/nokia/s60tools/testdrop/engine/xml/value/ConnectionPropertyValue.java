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


package com.nokia.s60tools.testdrop.engine.xml.value;

import org.eclipse.core.runtime.IPath;

/**
 * Contains connection property values
 * 
 */
public class ConnectionPropertyValue {
	private String host;
	private int port;
	private String username;
	private String password;
	private String schedule;
	private String method;
	private IPath dropPath;

	/**
	 * Constructor
	 * 
	 * @param host
	 *            ATS server host name
	 * @param port
	 *            ATS server port
	 * @param username
	 *            server user name
	 * @param password
	 *            user's password
	 * @param schedule
	 *            schedule
	 * @param method
	 *            http method either POST or GET
	 * @param dropPath
	 *            target drop path
	 */
	public ConnectionPropertyValue(String host, int port, String username,
			String password, String schedule, String method, IPath dropPath) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.schedule = schedule;
		this.method = method;
		this.dropPath = dropPath;
	}

	/**
	 * Sets a new server host name
	 * 
	 * @param host
	 *            a new server host name
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Returns server host name
	 * 
	 * @return server host name
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets a new server port
	 * 
	 * @param port
	 *            a new server port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Returns server port
	 * 
	 * @return server port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets a new user name
	 * 
	 * @param username
	 *            a new user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns user name
	 * 
	 * @return user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets a new user's password
	 * 
	 * @param password
	 *            a new user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns user's password
	 * 
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets a new schedule time
	 * 
	 * @param schedule
	 *            a new schedule time
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * Returns schedule time
	 * 
	 * @return schedule time
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * Sets a new http method
	 * 
	 * @param method
	 *            a new http method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Returns http method
	 * 
	 * @return http method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets a new test drop path
	 * 
	 * @param dropPath
	 *            a new test drop path
	 */
	public void setDropPath(IPath dropPath) {
		this.dropPath = dropPath;
	}

	/**
	 * Returns test drop path
	 * 
	 * @return test drop path
	 */
	public IPath getDropPath() {
		return dropPath;
	}
}
