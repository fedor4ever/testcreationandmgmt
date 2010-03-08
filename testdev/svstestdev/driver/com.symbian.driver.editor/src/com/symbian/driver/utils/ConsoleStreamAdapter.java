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



package com.symbian.driver.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.ui.console.MessageConsoleStream;

import com.symbian.driver.provider.DriverEditPlugin;

/**
 * @author EngineeringTools
 * 
 */
public class ConsoleStreamAdapter extends PrintStream {
	private MessageConsoleStream stream;

	/**
	 * @param s
	 * @param aErr
	 */
	public ConsoleStreamAdapter(MessageConsoleStream s, final boolean aErr) {
		super(aErr ? System.err : System.out);
		stream = s;
	}

	/**
	 * Overidden PrintStream methods re-direct output.
	 * 
	 * @param arg0
	 */
	public ConsoleStreamAdapter(OutputStream arg0) {
		super(arg0);
	}

	public void print(boolean b) {
		stream.print(Boolean.toString(b));
	}

	public void print(char c) {
		stream.print(Character.toString(c));
	}

	public void print(char[] c) {
		stream.print(c.toString());
	}

	public void print(double d) {
		stream.print(Double.toString(d));
	}

	public void print(float f) {
		stream.print(Float.toString(f));
	}

	public void print(int i) {
		stream.print(Integer.toString(i));
	}

	public void print(Object o) {
		stream.print(o.toString());
	}

	public void print(String s) {
		stream.print(s);
	}

	public void println(boolean b) {
		stream.println(Boolean.toString(b));
	}

	public void println(char c) {
		stream.println(Character.toString(c));
	}

	public void println(char[] c) {
		stream.println(c.toString());
	}

	public void println(double d) {
		stream.println(Double.toString(d));
	}

	public void println(float f) {
		stream.println(Float.toString(f));
	}

	public void println(int i) {
		stream.println(Integer.toString(i));
	}

	public void println(Object o) {
		stream.println(o.toString());
	}

	public void println(String s) {
		stream.println(s);
	}

	public void println() {
		stream.println();
	}

	public boolean checkError() {
		return false;
	}

	// override for completeness.
	/**
	 * @see java.io.PrintStream#close()
	 */
	public void close() {
		try {
			stream.close();
		} catch (IOException lException) {
			DriverEditPlugin.INSTANCE.log(lException);
		}
	}

	/**
	 * @see java.io.PrintStream#flush()
	 */
	public void flush() {
		try {
			stream.flush();
		} catch (IOException lException) {
			DriverEditPlugin.INSTANCE.log(lException);
		}
	}

	/**
	 * @see java.io.PrintStream#setError()
	 */
	protected void setError() {
	}

	/**
	 * 
	 */
	public void write() {
	}

	/**
	 * @param b
	 */
	public void write(byte b) {
	}
}
