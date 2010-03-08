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

package com.symbian.utils.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.apache.commons.cli.ParseException;

/**
 * @author EngineeringTools
 * 
 */
public class ConfigUtils {

	private static final String NO_MANIFEST_FOUND = "No manifest found.";

	/** The preferences that are user dependent. */
	private Preferences iPrefrences;

	/** Name of the preferences node. */
	private final String iNodeName;

	/** Stored configuration. */
	private final HashMap<String, Comparable> iSavedConfig;

	/** Configuration Map. */
	private final Map iConfigMap;

	/** Preference Literals. */
	private final String[] iPreferenceLiterals;

	/** Generic Logger. */
	private static final Logger LOGGER = Logger.getLogger(ConfigUtils.class.getName());

	/**
	 * @param aConfigMap
	 * @param aPreferenceAddress
	 * @param aPreferenceLiterals
	 * @throws IOException
	 */
	protected ConfigUtils(String aPreferenceAddress, String[] aPreferenceLiterals) throws IOException {
		iNodeName = aPreferenceAddress;
		iConfigMap = new HashMap();
		iSavedConfig = new HashMap();
		iPreferenceLiterals = aPreferenceLiterals;

		iPrefrences = Preferences.userRoot().node(iNodeName);
		// if created for the first time, it needs to be flushed.
		try {
			iPrefrences.flush();
		} catch (BackingStoreException lBackingStoreException) {
			LOGGER.warning("Failed to flush the configuration: " + lBackingStoreException.getMessage());
		}
		// Register the listener for prefs changes
		iPrefrences.addPreferenceChangeListener(new PreferenceChangeListener() {
			public void preferenceChange(PreferenceChangeEvent evt) {
				try {
					iPrefrences.flush();
				} catch (BackingStoreException lBackingStoreException) {
					LOGGER.warning("Failed to flush the configuration: " + lBackingStoreException.getMessage());
				}
			}
		});
	}
	
	/**
	 * completeConfigFromStore
	 * add any extra elements from the store. For example, if the plugins have been activated before, there settings 
	 * would have been loaded by their activators.
	 *
	 */
	protected void completeConfigFromStore() {
		try {
			TreeSet<String> storeKeys = new TreeSet<String>();
			storeKeys.addAll(Arrays.asList(iPrefrences.keys()));
			TreeSet<String> localKeys = new TreeSet<String>();
			localKeys.addAll(Arrays.asList(iPreferenceLiterals));
			
			for (String key : storeKeys) {
				if (!localKeys.contains(key)) {
					addConfig(key, iPrefrences.get(key, ""), new CheckGetConfig(), new CheckSetConfig(), String.class);
				}				
			}			
		} catch (BackingStoreException e) {
			LOGGER.log(Level.SEVERE, "Could not initialise the config " + e.getMessage());
		}
	}
	

	/**
	 * @param aKey
	 * @param aKeyLiteral
	 * @param aDefault
	 * @param aCheckGetConfig
	 * @param aCheckSetConfig
	 * @param aType
	 */
	public void addConfig(final int aKey, final String aDefault, final CheckGetConfig aCheckGetConfig,
			final CheckSetConfig aCheckSetConfig, final Class aType) {
		iConfigMap.put(iPreferenceLiterals[aKey], new Object[] { aCheckGetConfig, aCheckSetConfig, aType });

		String lValue = iPrefrences.get(iPreferenceLiterals[aKey], aDefault);
		if (lValue == null) {
			throw new NullPointerException("The preference: " + aKey + " is NULL");
		} else if (aDefault.equals(lValue)) {
			iPrefrences.put(iPreferenceLiterals[aKey], aDefault);
			saveLocalPreference(iPreferenceLiterals[aKey], aDefault, aType);
		} else {
			saveLocalPreference(iPreferenceLiterals[aKey], lValue, aType);
		}
	}

	public void addConfig(final String aKey, final String aDefault, final CheckGetConfig aCheckGetConfig,
			final CheckSetConfig aCheckSetConfig, final Class aType) {
		String lKey = aKey.toLowerCase();
		iConfigMap.put(lKey, new Object[] { aCheckGetConfig, aCheckSetConfig, aType });

		String lValue = iPrefrences.get(lKey, aDefault);
		if (lValue == null) {
			throw new NullPointerException("The preference: " + lKey + " is NULL");
		} else if (aDefault.equals(lValue)) {
			iPrefrences.put(lKey, aDefault);
			saveLocalPreference(lKey, aDefault, aType);
		} else {
			saveLocalPreference(lKey, lValue, aType);
		}
	}
	
	public boolean hasConfig(String aConfigElement) {
		if (iConfigMap.containsKey(aConfigElement.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param aKeyLiteral
	 * @param aDefault
	 * @param aType
	 */
	private void saveLocalPreference(final String aKeyLiteral, final String aDefault, final Class aType) {
		if (String.class.equals(aType)) {
			iSavedConfig.put(aKeyLiteral, aDefault);
		} else if (Boolean.class.equals(aType)) {
			iSavedConfig.put(aKeyLiteral, new Boolean(aDefault));
		} else if (File.class.equals(aType)) {
			iSavedConfig.put(aKeyLiteral, new File(aDefault));
		} else if (Integer.class.equals(aType)) {
			iSavedConfig.put(aKeyLiteral, new Integer(aDefault));
		}
	}

	/**
	 * Clear user prefrences from the configuration
	 * 
	 * @throws IOException
	 *             If the operation fails.
	 */
	public void clearConfig() throws IOException {
		try {
			iPrefrences.clear();
			iSavedConfig.clear();
		} catch (BackingStoreException lBSE) {
			throw new IOException("Can not clear user prefs from configuration" + lBSE.getMessage());
		}
	}

	/**
	 * Save the Configuration settings to files.
	 * 
	 * @param aPrefFile
	 *            The Java Preference file to export/save.
	 * 
	 * @throws IOException
	 *             If the saving doesn't work.
	 */
	public void exportConfig(final File aPrefFile) throws IOException {
		try {
			iPrefrences.exportNode(new FileOutputStream(aPrefFile));
		} catch (FileNotFoundException lFileNotFoundException) {
			throw new IOException("Cannot create new properties file: " + aPrefFile.getPath() + ": "
					+ lFileNotFoundException.getMessage());
		} catch (IOException lIOException) {
			throw new IOException("Properties file I/O error: " + aPrefFile.getPath() + ": "
					+ lIOException.getMessage());
		} catch (BackingStoreException lBackingStoreException) {
			throw new IOException("Backing Store error: " + aPrefFile.getPath() + ": "
					+ lBackingStoreException.getMessage());
		}
	}

	/**
	 * load the Configuration settings from files.
	 * 
	 * @param aPrefFile
	 *            The Java Preference file to import/load.
	 * 
	 * @throws IOException
	 *             If the load doesn't work.
	 */
	public void importConfig(final File aPrefFile) throws IOException {
		try {
			Preferences.importPreferences(new FileInputStream(aPrefFile));
			iPrefrences = Preferences.userRoot().node(iNodeName);
			
		} catch (FileNotFoundException lFileNotFoundException) {
			throw new IOException("Cannot create new properties file: " + aPrefFile.getPath() + ": "
					+ lFileNotFoundException.getMessage());
		} catch (IOException lIOException) {
			throw new IOException("Properties file I/O error: " + aPrefFile.getPath() + ", "
					+ lIOException.getMessage());
		} catch (InvalidPreferencesFormatException lIPFE) {
			throw new IOException("Prefrences files are not valid: " + aPrefFile.getPath() + ", " + lIPFE.getMessage());
		}
	}

	/**
	 * Print the Configuration setting
	 * 
	 * @param aLogger
	 *            If <code>true</code> then prints to logger, else if
	 *            <code>false</code> then prints to STDOUT.
	 * @throws IOException
	 */
	public void printConfig(final boolean aLogger) throws IOException {
		try {

			StringBuffer lOutput = new StringBuffer();

			lOutput.append("\n");
			
			TreeSet<String> sortedKeys = new TreeSet<String>();
			sortedKeys.addAll(iSavedConfig.keySet());
			
			//sortedKeys.addAll(Arrays.asList(iPrefrences.keys()));


			for (Iterator lConfigIter = sortedKeys.iterator(); lConfigIter.hasNext();) {
				String lKey = (String) lConfigIter.next();
				//for lite version enhancement. Depreciate "sysbin", instead use "statLite"
				if (lKey.equalsIgnoreCase("platsec"))  //PlatSec will not be used and should not be displayed to user
					continue;
				String lKeyDisplayName = lKey.equalsIgnoreCase("sysbin") ? "statlite" : lKey;
				String lMessage = "Preference " + lKeyDisplayName + ":" + (lKeyDisplayName.length() < 12 ? "\t\t" : "\t")
						+ iSavedConfig.get(lKey);
				if (aLogger) {
					lOutput.append(lMessage + "\n");
				} else {
					System.out.println(lMessage);
				}
			}

			if (aLogger) {
				LOGGER.info(lOutput.toString());
			}

		} catch (SecurityException lSecurityException) {
			throw new IOException("Security exception: " + lSecurityException.getMessage());
		}
	}

	/**
	 * Retrieves the preference for the local storage intially created from the
	 * registry.
	 * 
	 * @param aKey
	 *            The key of which preference to create.
	 * @return The preference requested according to the key.
	 * @throws ParseException
	 */
	public String getPreference(final int aKey) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!String.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the getPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Getting Preference " + aKey + ": " + iSavedConfig.get(aKeyLiteal));

		return (String) ((CheckGetConfig) lConfigMap[0]).get(aKeyLiteal, iSavedConfig.get(aKeyLiteal));
	}
	
	/**
	 * Retrieves the preference for the local storage intially created from the
	 * registry.
	 * 
	 * @param aKey
	 *            The key of which preference to create.
	 * @return The preference requested according to the key.
	 * @throws ParseException
	 */
	public String getPreference(final String aKey) throws ParseException {
		String aKeyLiteal = aKey;
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			return null;
		}
		if (!String.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the getPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Getting Preference " + aKey + ": " + iSavedConfig.get(aKeyLiteal));

		return (String) ((CheckGetConfig) lConfigMap[0]).get(aKeyLiteal, iSavedConfig.get(aKeyLiteal));
	}
	

	/**
	 * @param aKey
	 * @param aValue
	 * @throws ParseException
	 */
	public String setPreference(final int aKey, final String aValue) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!String.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Setting Preference " + aKey + ": " + aValue);

		String lCheckedValue = (String) ((CheckSetConfig) lConfigMap[1]).set(aKeyLiteal, aValue);

		iSavedConfig.put(aKeyLiteal, lCheckedValue);
		iPrefrences.put(aKeyLiteal, lCheckedValue);

		return lCheckedValue;
	}

	/**
	 * @param aKey
	 * @param aValue
	 * @throws ParseException
	 */
	public String setPreference(final String aKey, final String aValue) throws ParseException {
		String aKeyLiteal = aKey.toLowerCase();
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!String.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Setting Preference " + aKey + ": " + aValue);

		String lCheckedValue = (String) ((CheckSetConfig) lConfigMap[1]).set(aKeyLiteal, aValue);

		iSavedConfig.put(aKeyLiteal, lCheckedValue);
		iPrefrences.put(aKeyLiteal, lCheckedValue);

		return lCheckedValue;
	}

	/**
	 * @param aKey
	 * @return <code>true</code> if the preference for the Key is
	 *         <code>true</code>, <code>false</code> otherwise.
	 * @throws ParseException
	 */
	public boolean isPreference(final int aKey) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!Boolean.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Getting Preference " + aKey + ": " + iSavedConfig.get(aKeyLiteal));

		return ((Boolean) ((CheckGetConfig) lConfigMap[0]).get(aKeyLiteal, iSavedConfig.get(aKeyLiteal)))
				.booleanValue();
	}

	/**
	 * @param aKey
	 * @param aValue
	 * @throws ParseException
	 */
	public boolean setPreferenceBoolean(final int aKey, final boolean aValue) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!Boolean.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Setting Preference " + aKeyLiteal + ": " + aValue);

		Boolean lCheckedValue = (Boolean) ((CheckSetConfig) lConfigMap[1]).set(aKeyLiteal, new Boolean(aValue));

		iSavedConfig.put(aKeyLiteal, lCheckedValue);
		iPrefrences.putBoolean(aKeyLiteal, lCheckedValue.booleanValue());

		return lCheckedValue.booleanValue();
	}

	/**
	 * @param aKey
	 * @return The integer stored in the preferences by the key.
	 * @throws ParseException
	 */
	public int getPreferenceInteger(final int aKey) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKey);
		}
		if (!Integer.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Getting Preference " + aKeyLiteal + ": " + iSavedConfig.get(aKeyLiteal));

		return ((Integer) ((CheckGetConfig) lConfigMap[0]).get(aKeyLiteal, iSavedConfig.get(aKeyLiteal))).intValue();
	}

	/**
	 * @param aKey
	 * @param aValue
	 * @throws ParseException
	 */
	public int setPreferenceInteger(final int aKey, final int aValue) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!Integer.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Setting Preference " + aKeyLiteal + ": " + aValue);

		Integer lCheckedValue = (Integer) ((CheckSetConfig) lConfigMap[1]).set(aKeyLiteal, new Integer(aValue));

		iSavedConfig.put(aKeyLiteal, lCheckedValue);
		iPrefrences.putInt(aKeyLiteal, lCheckedValue.intValue());

		return lCheckedValue.intValue();
	}

	/**
	 * @param aKey
	 * @return The file stored in the preferences by the key.
	 * @throws ParseException
	 */
	public File getPreferenceFile(final int aKey) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!File.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Getting Preference " + aKeyLiteal + ": " + iSavedConfig.get(aKeyLiteal));

		return (File) ((CheckGetConfig) lConfigMap[0]).get(aKeyLiteal, iSavedConfig.get(aKeyLiteal));
	}

	/**
	 * @param aKey
	 * @param aValue
	 * @throws ParseException
	 */
	public File setPreferenceFile(final int aKey, final File aValue) throws ParseException {
		String aKeyLiteal = iPreferenceLiterals[aKey];
		Object[] lConfigMap = (Object[]) iConfigMap.get(aKeyLiteal);

		if (lConfigMap == null) {
			throw new NullPointerException("Incorrect key: " + aKeyLiteal);
		}
		if (!File.class.equals(lConfigMap[2])) {
			throw new ParseException("This preference " + aKeyLiteal
					+ " cannot use the setPreference() method. It must be of type " + lConfigMap[2].toString());
		}

		LOGGER.finest("Setting Preference " + aKeyLiteal + ": " + aValue);

		File lCheckedValue = (File) ((CheckSetConfig) lConfigMap[1]).set(aKeyLiteal, aValue);

		iSavedConfig.put(aKeyLiteal, lCheckedValue);
		iPrefrences.put(aKeyLiteal, lCheckedValue.toString());

		return lCheckedValue;
	}
	

	private static String iName = NO_MANIFEST_FOUND;
	public static void setName(String aName) {
		iName = aName;
	}

	/**
	 * Get Name
	 * 
	 * @return the name of the program e.g. TestDriver
	 */
	public static String getName() {
		return iName;
	}

	private static String iVersion = NO_MANIFEST_FOUND;
	public static void setVersion(String aVersion) {
		iVersion = aVersion;
	}
	
	/**
	 * get version number
	 * 
	 * @return the version string of the program e.g. 1.00.1000
	 */
	public static String getVersion() {

		return iVersion;
	}

	
	private static String iCopyright = NO_MANIFEST_FOUND;
	public static void setCopyright(String aCopyright) {
		iCopyright = aCopyright;
	}
	
	/**
	 * get copyright
	 * 
	 * @return the copyright of the program
	 */

	public static String getCopyright() {

		return iCopyright;
	}
}
