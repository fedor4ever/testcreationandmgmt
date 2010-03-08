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


package com.symbian.utils;

import java.io.File;

/**
 * Singleton class that provides info on the Symbian OS.
 * 
 * @author EngineeringTools
 */
public final class Epoc {
	
	/** Literal for 'armv5'. */
	public final static String ARMV5 = "armv5";
	
	/** Literal for 'thumb'. */
	public final static String THUMB = "thumb";
	
	/** Literal for 'arm4'. */
	public final static String ARM4 = "arm4";
	
	/** Literal for 'winscw'. */
	public final static String WINSCW = "winscw";
	
	/** Literal for 'wins'. */
	public static final String WINS = "wins";
	
	/** Literal for 'udeb'. */
	public final static String UDEB = "udeb";
	
	/** Literal for 'urel'. */
	public final static String UREL = "urel";
	
	/** Possible Targets under EPOC. */
	private static final String[] TARGETS = { "thumb", "arm4", "armv5", "winscw", "wins" };

	/** Possible Target Variants under EPOC. */
	private static final String[] VARIANTS = { "udeb", "urel" };
	
	/** Possible synchronisation modes. */
	private static final String[] MODES = { "sync", "async" };

	/** Possible binary extensions in EPOC. */
	private static final String[] BINARYEXTENSIONS = { "exe", "dll", "ldd", "app" };
	
	/** Possible binary extensions in EPOC. */
	private static final String[] KERNEL = { "eka1", "eka2" };

	/**
	 * Checks if the target is valid.
	 * 
	 * @param aTarget
	 *            The target to check.
	 * @return <code>true</code> if the target is valid, <code>false</code>
	 *         otherwise
	 */
	public static boolean isTargetValid(final String aTarget) {	
		return true;
	}

	/**
	 * Checks if the variant is valid.
	 * 
	 * @param aVariant
	 *            The variant to check.
	 * @return <code>true</code> if the variant is valid, <code>false</code>
	 *         otherwise
	 */
	public static boolean isVariantValid(final String aVariant) {
		for (int lCounter = 0; lCounter < VARIANTS.length; lCounter++) {
			if (VARIANTS[lCounter].compareToIgnoreCase(aVariant) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the synchornisation mode is valid.
	 * 
	 * @param aMode
	 *            The mode to check.
	 * @return <code>true</code> if the mode is valid, <code>false</code>
	 *         otherwise
	 */
	public static boolean isModeValid(final String aMode) {
		for (int lCounter = 0; lCounter < MODES.length; lCounter++) {
			if (MODES[lCounter].compareToIgnoreCase(aMode) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the target is hardware or software.
	 * 
	 * @param aTarget
	 *            The target to check.
	 * @return <code>true</code> if the target is hardware, <code>false</code>
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             if the target is invalid.
	 */
	public static boolean isTargetEmulator(final String aTarget) throws IllegalArgumentException {
		if (!isTargetValid(aTarget)) {
			throw new IllegalArgumentException("Target is invalid.");
		}

		return aTarget.equalsIgnoreCase(WINS) || aTarget.equalsIgnoreCase(WINSCW);
	}

	/**
	 * Checks the Epoc Environment.
	 * @param aEpocroot 
	 * @return <code>true</code> if the Epoc Root is valid, <code>false</code> otherwise.
	 * 
	 * @throws IllegalArgumentException
	 *             If the epoc environment is not correct.
	 */
	public static boolean isEpocrootValid(final File aEpocroot) throws IllegalArgumentException {
		if (!(aEpocroot.isDirectory() || new File(aEpocroot.getName() + File.separator + "epoc32" + File.separator + "tools" + File.separator + "abld.pl")
				.exists())) {
			return false;
		}

		return true;
	}

	/**
	 * Determines whether a given file seems to be executable according to its
	 * extension. Beware that some files are binary but are not executable<br>
	 * Similarilly, it's not because a file has an executable extension that it
	 * has to be moved to /sys/bin
	 * 
	 * @param aEpocFileName
	 *            The filename to check if executable.
	 * @return <code>true</code> if the file's extension is for executable
	 *         file
	 */
	public static boolean hasExecutableExtension(final String aEpocFileName) {
		if (aEpocFileName == null) {
			return false;
		}
		String lAllLowerCase = aEpocFileName.toLowerCase();
		for (int lCounter = 0; lCounter < BINARYEXTENSIONS.length; lCounter++) {
			if (lAllLowerCase.endsWith("." + BINARYEXTENSIONS[lCounter])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param aString
	 * @return 
	 */
	public static boolean isKernelValid(String aString) {
		for (int lIter = 0; lIter < KERNEL.length; lIter++) {
			if (KERNEL[lIter].equalsIgnoreCase(aString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param aBuildNumber
	 * @return 
	 */
	public static boolean isEka1(String aBuildNumber) {
		if (aBuildNumber.endsWith("7.0") 
				|| aBuildNumber.endsWith("7.0s") 
				|| aBuildNumber.endsWith("8.0a")
				|| aBuildNumber.endsWith("8.1a")) {
			return true;
		}
	
		return false;
	}
	
	/**
	 * @param aBuildNumber
	 * @return aBoolean
	 */
	public static boolean is9x(String aBuildNumber) {
		if (isEka1(aBuildNumber) || aBuildNumber.endsWith("8.1b")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Validates the if a build number is Symbian OS version 9.2+.
	 * 
	 * @param aBuildNumber A build number to check against.
	 * @return aBoolean <code>true</code> if the build number is 9.2 or greater, <code>false</code> otherwise.
	 */
	public static boolean is92plus(String aBuildNumber) {
		if (aBuildNumber != null && is9x(aBuildNumber) && ! aBuildNumber.endsWith("9.1")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Validates the if a build number is Symbian OS version 9.3+.
	 * 
	 * @param aBuildNumber A build number to check against.
	 * @return aBoolean <code>true</code> if the build number is 9.3 or greater, <code>false</code> otherwise.
	 */
	public static boolean is93plus(String aBuildNumber) {
		if (aBuildNumber != null && is9x(aBuildNumber) && ! aBuildNumber.endsWith("9.1") && ! aBuildNumber.endsWith("9.2")) {
			return true;
		}
		
		return false;
	}

	/**
	 * Validates a transport string.
	 * 
	 * @param aTransport The transport string to validate.
	 * @return <code>true</code> if the transport is a valid STAT transport, <code>false</code> otherwise.
	 */
	public static boolean isTransportValid(String aTransport) {
		if (aTransport.toLowerCase().startsWith("serial")
				|| aTransport.toLowerCase().startsWith("bt")
				|| aTransport.toLowerCase().startsWith("usb")
				|| aTransport.toLowerCase().startsWith("tcp")) {
			return true;
		}
		return false;
	}
}
