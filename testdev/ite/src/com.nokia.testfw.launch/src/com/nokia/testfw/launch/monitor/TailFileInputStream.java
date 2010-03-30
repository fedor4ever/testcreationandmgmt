/*
 * Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
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
package com.nokia.testfw.launch.monitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * an input stream, which load contents from file on the fly like 'tail -f'
 * command on linux
 * 
 */
public class TailFileInputStream extends InputStream {

	RandomAccessFile file;
	long offset;

	/**
	 * 
	 * @param file
	 *            , the file object
	 * @throws IOException
	 */
	public TailFileInputStream(String filename) throws IOException {
		super();
		file = new RandomAccessFile(filename, "r");
		offset = file.length();
		file.seek(offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException {
		checkTail();
		byte b[] = new byte[1];
		int len = read(b, 0, 1);
		if (len < 0)
			return len;
		else
			return b[0];
	}

	public int read(byte[] b) throws IOException {
		checkTail();
		return read(b, 0, b.length);
	}

	public int read(byte[] b, int off, int len) throws IOException {
		checkTail();
		int read = file.read(b, off, len);
		if (read > -1) {
			offset = offset + read;
		}
		return read;
	}

	@Override
	public long skip(long n) throws IOException {
		checkTail();
		long skip = (long) file.skipBytes((int) n);
		offset = offset + skip;
		return skip;
	}

	@Override
	public int available() throws IOException {
		checkTail();
		long available = file.length() - offset;
		if (available > 0) {
			if (available > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			} else {
				return (int) available;
			}
		} else {
			return 0;
		}
	}

	private void checkTail() throws IOException {
		if (file.length() < offset) {
			reset();
		}
	}

	public void reset() throws IOException {
		offset = 0L;
		file.seek(offset);
	}

	@Override
	public void close() throws IOException {
		file.close();
	}

}
