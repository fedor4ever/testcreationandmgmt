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



package com.symbian.driver.remoting.packaging.build;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.symbian.driver.Task;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.util.DriverSwitch;
import com.symbian.utils.Epoc;
import com.symbian.utils.Zipper;

/**
 * Package builder class.
 * 
 * @author EngineeringTools
 */
public class PackageBuilder implements Builder {

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(PackageBuilder.class.getName());

	private static final String EPOC32 = "epoc32";

	/**
	 * Builds a Test Pacakge.
	 * 
	 * @see com.symbian.driver.remoting.packaging.build.Builder#Build(java.lang.String)
	 * @param aTestPackage
	 *            String : a test package path name.
	 */
	public void Build(File aTestPackage) {
		// get TestDriver properties
		String lReposRoot = null;
		String lXmlRoot = null;
		String lEpocRoot = null;
		File xmlZip = new File("Xml.zip");
		File reposZip = new File("Repository.zip");
		File depZip = new File("Dependencies.zip");
		File manifest = new File("Manifest.mf");
		File StatZip = new File("Stat.zip");

		String lPlatform = null; // winscw
		String lBuild = null; // urel
		String lSuiteFragment = null; // full URI
		boolean lPlatsec = false;
		boolean lTestexec = false;
		String lKernel = null;
		String lBuildNumber = null;
		URI lSuite = null;
		List<URI> lUriList = null;

		try {
			TDConfig CONFIG = TDConfig.getInstance();
			lEpocRoot = CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).getCanonicalPath(); // m:
			lReposRoot = CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT).getCanonicalPath(); // d:/td2_testing/repository
			File lXmlR = CONFIG.getPreferenceFile(TDConfig.XML_ROOT);
			lXmlRoot = lXmlR.getCanonicalPath(); // m:/epoc32/TestDriver/Plattest

			lPlatform = CONFIG.getPreference(TDConfig.PLATFORM);
			lBuild = CONFIG.getPreference(TDConfig.VARIANT);
			//lPlatsec = CONFIG.isPreference(TDConfig.PLATSEC);
			lPlatsec = ! CONFIG.isPreference(TDConfig.SYS_BIN);
			lTestexec = CONFIG.isPreference(TDConfig.TEST_EXECUTE);
			lKernel = CONFIG.getPreference(TDConfig.KERNEL);
			lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
			lSuite = CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS);
			lSuiteFragment = lSuite.fragment();
			// build the .driver path

			if (lSuite.fileExtension() == null) {
				// CASE: no .driver

				String lRoot = lSuite.toString();

				int lLast = (lRoot.indexOf('.') > 0) ? lRoot.indexOf('.') : lRoot.length();

				File lRootDriver = new File(lXmlR, lRoot.substring(1, lLast).concat(".driver"));

				if (lRootDriver.isFile()) {
					lSuite = URI.createFileURI(lRootDriver.getCanonicalPath());
					// the fragment is correct because x.driver exists
				} else {
					// x.driver does not exist
					File[] lDriverFiles = lXmlR.listFiles(new FileFilter() {
						public boolean accept(File lFile) {
							if (lFile.getName().endsWith(".driver")) {
								return true;
							}
							return false;
						}
					});

					if (lDriverFiles.length == 1) {
						// run the one
						lSuite = URI.createFileURI(lDriverFiles[0].getCanonicalPath());
						lSuiteFragment = lDriverFiles[0].getName().replaceAll("\\.driver", "") + "." + lSuiteFragment;

					} else {
						// more complain + return
						LOGGER.log(Level.SEVERE, "There are either no or more than one .driver files in : "
								+ lXmlR.toString());
						return;
					}
				}
			} else {
				lSuite = URI.createFileURI(lSuite.devicePath());
			}

		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Could not get values from config", lIOException);
			return;
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "Could not get values from config", e);
			return;
		}

		if (lBuildNumber == null) {
			LOGGER.log(Level.SEVERE, "Could not get the build number.");
			return;
		}
		if (lKernel == null) {
			LOGGER.log(Level.SEVERE, "Kernel is not available in the configuration.");
			return;
		}

		if (lSuiteFragment == null) {
			LOGGER.log(Level.SEVERE, "A Suite/test must be provided.");
			return;
		}

		try {
			// get all refrences.
			Task lTask = ResourceLoader.load(lSuite);

			lUriList = new ArrayList<URI>();

			for (Iterator lIterator = lTask.eAllContents(); lIterator.hasNext();) {
				Task lReferenceTask = (Task) new DriverSwitch().doSwitch((EObject) lIterator.next());
				if (lReferenceTask != null) {
					lUriList.add(lReferenceTask.eResource().getURI());
				}
			}

		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Could not get all refrences from " + lSuite.toFileString(), lIOException);
			return;
		}

		// where to pick the repository from
		File lRepos = new File((lReposRoot + File.separator + lBuildNumber + File.separator + lPlatform
				+ File.separator + lBuild + File.separator + lSuiteFragment.replace('.', File.separatorChar))
				.replaceAll("\\\\+", "\\\\"));
		if (!lRepos.isDirectory()) {
			LOGGER.log(Level.SEVERE, "Repository root " + lRepos.toString() + " does not exist.");
			return;
		}

		// where to pick the dependencies from
		String lDep = (lEpocRoot + File.separator + EPOC32 + File.separator + "release" + File.separator + lPlatform
				+ File.separator + lBuild + File.separator).replaceAll("\\\\+", "\\\\");
		if (!new File(lDep).isDirectory()) {
			LOGGER.log(Level.SEVERE, "Epoc tree " + lDep + " does not exist.");
			return;
		}

		File lXml = new File(lSuite.toFileString());
		if (!lXml.exists()) {
			LOGGER.log(Level.SEVERE, "XML file " + lXml.toString() + " does not exist");
			return;
		}
		Zipper lZip = new Zipper();
		// Add The main xml file.
		lZip.addFile(lXml);
		// Add files referenced made by the main file.
		for (URI lItem : lUriList) {

			// get path part only path like ${variable}\... are allowed
			String lItemPathString = lItem.path();
			// remove \/ from beginning
			lItemPathString = lItemPathString.replaceFirst("^[\\/]+", "");
			if (lItemPathString.startsWith("\\$\\{\\w+\\}")) {
				LOGGER.log(Level.SEVERE, "Reference : " + lItem.toFileString() + " is not allowed.");
				return;
			}
			// do the substitution of variables

			String lRealFilePath = ModelUtils.subsituteVariables(lItemPathString);
			File lRealFile = new File(lRealFilePath);
			if (!lRealFile.isFile()) {
				LOGGER.log(Level.WARNING, "XML file " + lItem.toFileString() + " does not exist");
			} else {
				lZip.addFile(lRealFile, lItemPathString);
			}
		}

		// Zip the xml file

		lZip.zip(xmlZip, lXmlRoot);
		lZip.clear();

		// Zip the repository
		// pick the files from lRepos downward through the fragment
		File lStart = new File((lReposRoot + File.separator + lBuildNumber + File.separator + lPlatform
				+ File.separator + lBuild).replaceAll("\\\\+", "\\\\"));
		String[] lFragmentBits = lSuiteFragment.split("\\.");
		File[] lFiles = null;
		for (int i = 0; i < lFragmentBits.length; i++) {
			lStart = new File(lStart, lFragmentBits[i]);
			if (i == lFragmentBits.length - 1) {
				lFiles = listFilesAsArray(lStart, null, true); // the whole
																// tree
			} else {
				lFiles = listFilesAsArray(lStart, null, false); // just files
			}
			for (int j = 0; j < lFiles.length; j++) {
				if (lFiles[j].isFile()) {
					if (lPlatsec) {
						if (lFiles[j].getName().toLowerCase().endsWith(".pkg")
								|| lFiles[j].getName().toLowerCase().endsWith(".sis")) {
							lZip.addFile(lFiles[j]);
						}
					} else {
						if (!lFiles[j].getName().toLowerCase().endsWith(".sis")
								&& !lFiles[j].getName().toLowerCase().endsWith(".pkg")) {
							lZip.addFile(lFiles[j]);
						}
					}
				}
			}
		}
		lZip.zip(reposZip, lReposRoot);
		lZip.clear();

		// Zip dependencies
		final String lEpoc32DataZSystemData = (lEpocRoot + File.separator
				+ com.symbian.driver.core.environment.ILiterals.EPOC32 + File.separator
				+ com.symbian.driver.core.environment.ILiterals.DATA + File.separator + "z" + File.separator
				+ com.symbian.driver.core.environment.ILiterals.SYSTEM + File.separator
				+ com.symbian.driver.core.environment.ILiterals.DATA + File.separator).replaceAll("\\\\+", "\\\\");

		// add testexecute dependencies.
		if (!Epoc.isTargetEmulator(lPlatform)) {
			String[] tefFiles = null;
			Map<String,String> tefOptFiles = null;
			final String lEpoc32Tools = EPOC32 + File.separator + "tools" + File.separator;

			if (lTestexec) {
				try {
					String[] lTefFiles = TDConfig.getInstance().getTEFDependencies();
					//add optional files into the array
					//<file,condition> pair in lTefOptFiles, get set of keys/files
					tefOptFiles = TDConfig.getInstance().getTEFOptionalDependencies();
					Set<String> lTefOptFilesSet = tefOptFiles.keySet();
					String[] lTefOptFiles = lTefOptFilesSet.toArray(new String[tefOptFiles.size()]);
					
					tefFiles = new String[lTefFiles.length + lTefOptFiles.length];
					System.arraycopy(lTefFiles, 0, tefFiles, 0, lTefFiles.length);
					System.arraycopy(lTefOptFiles, 0, 
										tefFiles, lTefFiles.length,
										lTefOptFiles.length);
				} catch (ParseException lParseException) {
					LOGGER.log(Level.SEVERE, lParseException.getMessage(), lParseException);
				}
				for (int i = 0; i < tefFiles.length; i++) {
					if (tefFiles[i].endsWith(".ini")) {
						lZip.addFile(new File(lEpoc32DataZSystemData, tefFiles[i]));
					} else {
						File lFile = new File(lDep, tefFiles[i]);
						if (lFile.exists()) {
							lZip.addFile(lFile);
						} else {
							LOGGER.warning("File does not exist : " + lFile);
						}
					}
				}
			}
			if (lPlatsec && Epoc.is9x(lBuildNumber)) {
				lZip.addFile(new File(lEpocRoot, lEpoc32Tools + "makesis.exe"));
				lZip.addFile(new File(lEpocRoot, lEpoc32Tools + "signsis.exe"));
			}

			lZip.addFile(new File(lEpocRoot, EPOC32 + File.separator + "data" + File.separator + "buildinfo.txt"));

			LOGGER.fine("Generating " + depZip.getName());

			lZip.zip(depZip, lEpocRoot);
			lZip.clear();

			// add stat.dll, symbianUsb.dll, buildinfo.txt, stat.ini,
			// makesis.exe and signsis.exe:
			// the following files should be installed with TD2
			lZip.addFile(new File(lEpoc32DataZSystemData, "stat.ini"));
			lZip.addFile(new File(lEpocRoot, lEpoc32Tools + "stat" + File.separator + "stat.dll"));
			if (Epoc.is92plus(lBuildNumber)) {
				lZip.addFile(new File(lEpocRoot, lEpoc32Tools + "stat" + File.separator + "SymbianUsb.dll"));
			}
			LOGGER.fine("Generating " + StatZip.getName());
			lZip.zip(StatZip, lEpocRoot);
			lZip.clear();

		}

		// generate manifest file:

		Properties pr = new Properties();

		String header = "TestDriver Package file v2.0";

		pr.setProperty("suite", lSuiteFragment);
		pr.setProperty("xmldriver", lXml.getName());
		pr.setProperty("platform", lPlatform);
		pr.setProperty("build", lBuild);
		pr.setProperty("buildNumber", lBuildNumber);
		pr.setProperty("kernel", lKernel);

		try {
			FileOutputStream mf = new FileOutputStream(manifest);
			pr.store(mf, header);
			mf.close();
		} catch (IOException lE) {
			LOGGER.log(Level.SEVERE, "Error while generating manifest", lE);
		}

		// zip everything together

		if (xmlZip.exists()) {
			lZip.addFile(xmlZip);
		}
		if (reposZip.exists()) {
			lZip.addFile(reposZip);
		}
		if (depZip.exists()) {
			lZip.addFile(depZip);
		}
		if (manifest.exists()) {
			lZip.addFile(manifest);
		}
		if (StatZip.exists()) {
			lZip.addFile(StatZip);
		}

		File f = null;

		if (aTestPackage == null) {
			// No test package name defined before (first run)

			String lSuiteEnd = lSuiteFragment;
			if (lSuiteFragment.indexOf(".") != -1) {
				String[] lPaths = lSuiteFragment.split("\\.");
				lSuiteEnd = lPaths[lPaths.length - 1];
			}

			f = new File((lReposRoot + File.separator + lSuiteEnd + ".tpkg").replaceAll("\\\\+", "\\\\"));

		} else {
			f = aTestPackage;
		}

		lZip.zip(f, "");

		if (f.exists()) {
			LOGGER.info("Generated test package file v2.0: " + f.getAbsolutePath());
		} else {
			LOGGER.severe("Error generating test package file v2.0: " + f.getName());
		}

		if (!xmlZip.delete() || !reposZip.delete() || !manifest.delete() || !depZip.delete() || !StatZip.delete()) {
			LOGGER.fine("Could not delete Zip files");
		}
	}

	/**
	 * @param directory
	 * @param filter
	 * @param recurse
	 * @return
	 */
	public static File[] listFilesAsArray(File directory, FilenameFilter filter, boolean recurse) {
		Collection<File> lFiles = listFiles(directory, filter, recurse);

		File[] lArrayFiles = new File[lFiles.size()];
		return lFiles.toArray(lArrayFiles);
	}

	/**
	 * @param directory
	 * @param filter
	 * @param recurse
	 * @return
	 */
	public static Collection<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
		// List of files / directories
		Vector<File> files = new Vector<File>();

		// Get files / directories in the directory
		File[] entries = directory.listFiles();

		// Go over entries
		for (int i = 0; i < entries.length; i++) {
			File entry = entries[i];

			// If there is no filter or the filter accepts the
			// file / directory, add it to the list
			if (filter == null || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}

			// If the file is a directory and the recurse flag
			// is set, recurse into the directory
			if (recurse && entry.isDirectory()) {
				files.addAll(listFiles(entry, filter, recurse));
			}
		}

		// Return collection of files
		return files;
	}

}
