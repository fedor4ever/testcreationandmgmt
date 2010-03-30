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

package com.nokia.testfw.codegen;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class StringResourceLoader extends ResourceLoader {

	@Override
	public long getLastModified(Resource resource) {
		return 0;
	}

	@Override
	public InputStream getResourceStream(String s)
			throws ResourceNotFoundException {
		InputStream result = null;
		if (s == null || s.length() == 0) {
			throw new ResourceNotFoundException("template is not defined.");
		}
		result = new ByteArrayInputStream(s.getBytes());
		return result;
	}

	@Override
	public boolean isSourceModified(Resource resource) {
		return false;
	}

	@Override
	public void init(ExtendedProperties extendedproperties) {
	}

	public static String getString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			if (sb.length() == 0) {
				sb.append(line);
			} else {
				sb.append("\n").append(line);
			}
		}
		is.close();

		return sb.toString();
	}

}
