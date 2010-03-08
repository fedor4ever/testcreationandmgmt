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

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Session handler. Keeps session that is got from the ATS Server
 * Cookie
 * 
 */
public class SessionHandler extends CookieHandler {

	List<String> cookieList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.net.CookieHandler#get
	 */
	public Map<String, List<String>> get(URI uri,
			Map<String, List<String>> requestHeaders) throws IOException {
		Map<String, List<String>> cookieMap = new HashMap<String, List<String>>(
				requestHeaders);
		if (cookieList != null) {
			cookieMap.put("Cookie", cookieList); 
		}
		return Collections.unmodifiableMap(cookieMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see see java.net.CookieHandler#put
	 */
	public void put(URI uri, Map<String, List<String>> responseHeaders)
			throws IOException {
		if (cookieList == null) {
			cookieList = responseHeaders.get("Set-Cookie"); 
		}
	}
}
