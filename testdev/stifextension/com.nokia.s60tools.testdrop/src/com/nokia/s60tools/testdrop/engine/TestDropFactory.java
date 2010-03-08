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


package com.nokia.s60tools.testdrop.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.EpocEngineHelper;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.s60tools.testdrop.engine.connection.HttpConnection;
import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.engine.xml.TestDropXMLCreator;
import com.nokia.s60tools.testdrop.engine.xml.value.ConnectionPropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TestContentValue;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;
import com.nokia.s60tools.testdrop.util.TestModuleTypeRecognizer;

/**
 * Factory class for creating test drop package
 * 
 */
public class TestDropFactory implements Runnable {
	private final String ATS_FOLDER = "ATS3"; 
	private final String COMPONENTS_FOLDER = "components"; 
	private final String GENERAL_FOLDER = "general"; 
	private final String IMAGES_FOLDER = "images"; 
	private final String SLASH = "/"; 
	private final String DOUBLDE_BACK_SLASH = "\\"; 
	private final String ZIP_FILE_CREATION_FAILED = Messages
			.getString("TestDropFactory.zipFileCreationFailedException"); 
	private final String WINSCW_UDEB = "winscw_udeb"; 
	private final String WINSCW_UREL = "winscw_urel"; 
	private final String ARMV5_UDEB = "armv5_udeb"; 
	private final String ARMV5_UREL = "armv5_urel"; 
	private final String[] targets = { "winscw", "armv5", "WINSCW", "ARMV5" };    
	private final String[] variatns = { "udeb", "urel", "UDEB", "UREL" };    
	private String location;
	private String ROOT_FOLDER_NAME = "testdrop"; 
	private String TESTDROP_ZIP_NAME = "testDrop.zip"; 
	private final String TESTDROP_FILE_EXTENSION = ".zip"; 
	private final String XML_FILE_NAME = "test.xml"; 
	private final String SCRIPT_TEST_START = "[Test]"; 
	private final String SCRIPT_TEST_END = "[Endtest]"; 
	private final String PROPERTY_PLATFORM = "PLATFORM"; 
	private final String PROPERTY_BUILD = "BUILD"; 
	private final String debugVariant = "armv5_udeb";
	private final String releaseVariant = "armv5_urel";
	private IProject selectedProject;
	private IFile selectedCfgFile;
	private TestDropXMLCreator testDropXMLCreator;
	private IProgressMonitor progressMonitor;
	private IFolder dropFolder;
	private boolean needToFlashImages;
	private boolean deleteFromLocalDrive;
	private String[] filesToInclude;
	private boolean debugVariantOfTestCombinerActive;

	/**
	 * Constructs class with mandatory parameter for constructing test drop (other than
	 * test combiner test drop)
	 * 
	 * @param location
	 *            project location
	 * @param selectedProject
	 *            test drop will be constructed from selected project
	 * @param needToFlashImages
	 *            is need to add flash images to test drop
	 */
	public TestDropFactory(String location, IProject selectedProject,
			boolean needToFlashImages) {
		this.location = location;
		this.selectedProject = selectedProject;
		this.selectedCfgFile = null;
		filesToInclude = null;
		this.needToFlashImages = needToFlashImages;
		ROOT_FOLDER_NAME += "[" + System.currentTimeMillis() + "]";  
		TESTDROP_ZIP_NAME = ROOT_FOLDER_NAME + TESTDROP_FILE_EXTENSION;
	}
	
	/**
	 * Constructs class with mandatory parameter for constructing test combiner test drop
	 * 
	 * @param selectedCfgFile
	 * 			cfg file that was chosen from workspace.
	 * @param filesToInclude
	 * 			files that are needed to be included inside the test drop
	 * 			Those are dll-s and other files required by test combiner to run the whole test drop
	 */
	public TestDropFactory(IFile selectedCfgFile, String[] filesToInclude, boolean debugVariantActive) {
		this.selectedCfgFile = selectedCfgFile;
		this.filesToInclude = filesToInclude;
		this.location = selectedCfgFile.getProject().getLocation().toString();
		this.selectedProject = selectedCfgFile.getProject();
		ROOT_FOLDER_NAME += "[" + System.currentTimeMillis() + "]";  
		TESTDROP_ZIP_NAME = ROOT_FOLDER_NAME + TESTDROP_FILE_EXTENSION;
		debugVariantOfTestCombinerActive = debugVariantActive;
	}

	/**
	 * dispose method
	 */
	private void dispose() {
		if (testDropXMLCreator != null) {
			testDropXMLCreator.dispose();
			testDropXMLCreator = null;
		}
	}

	/**
	 * Method for copying files
	 * 
	 * @param src
	 *            source file
	 * @param dst
	 *            destination file
	 * @throws FileNotFoundException
	 *             if target file does not exist
	 */
	private void copyFile(File src, File dst) throws FileNotFoundException {
		FileChannel srcReader = null;
		FileChannel dstWriter = null;
		boolean cancelled = false;
		boolean isDstSameAsSrc = false;
		try {
			srcReader = new FileInputStream(src).getChannel();
			dstWriter = new FileOutputStream(dst).getChannel();
			long size = srcReader.size();
			// max copying size in windows is 64 Mb
			long maxSize = (64 * 1024 * 1024);
			long divisor = 16;
			long step = size / divisor;
			if (step == 0) {
				step = size;
			}
			if (step >= maxSize) {
				divisor = size / maxSize;
				divisor += 1;
				step = size / divisor;
			}

			long readed = 0;
			while (readed < size) {
				LogExceptionHandler.cancelIfNeed(progressMonitor);
				readed += srcReader.transferTo(readed, step, dstWriter);
			}
			if (srcReader.size() == dstWriter.size()) {
				isDstSameAsSrc = true;
			}
		} catch (FileNotFoundException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (IOException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (CancellationException e) {
			cancelled = true;
			LogExceptionHandler.log(e.getMessage());
		} finally {

			if (srcReader != null) {
				try {
					srcReader.close();
				} catch (IOException e) {
					LogExceptionHandler.log(e.getMessage());
				}
			}
			if (dstWriter != null) {
				try {
					dstWriter.close();
				} catch (IOException e) {
					LogExceptionHandler.log(e.getMessage());
				}
			}
			if (cancelled) {
				throw new CancellationException(LogExceptionHandler.CANCELLED);
			} else if (!isDstSameAsSrc) {
				throw new FileNotFoundException(
						Messages
								.getString("TestDropFactory.fileCopyingErrorException") 
								+ dst.getPath()
								+ Messages
										.getString("TestDropFactory.fileCopyingErrorDoesNotExitException")); 
			}
		}
	}

	/**
	 * Creates test drop folder structure
	 * 
	 * @param variant
	 *            folder name
	 * @throws Exception
	 *             if folder structure creation failed
	 */
	private void createFolderSkeleton(String variant) throws Exception {
		IFolder rootFolder = selectedProject.getFolder(ROOT_FOLDER_NAME);
		if (!rootFolder.exists()) {
			createFolder(rootFolder);
		}
		IFolder atsFolder = rootFolder.getFolder(ATS_FOLDER);
		if (!atsFolder.exists()) {
			createFolder(atsFolder);
		}
		IFolder componentsFolder = atsFolder.getFolder(COMPONENTS_FOLDER);
		if (!componentsFolder.exists()) {
			createFolder(componentsFolder);
		}
		IFolder variantFolder = componentsFolder.getFolder(variant);
		if (!variantFolder.exists()) {
			createFolder(variantFolder);
		}
		IFolder generalFolder = componentsFolder.getFolder(GENERAL_FOLDER);
		if (!generalFolder.exists()) {
			createFolder(generalFolder);
		}

		if (!rootFolder.exists() || !atsFolder.exists()
				|| !componentsFolder.exists() || !variantFolder.exists()
				|| !generalFolder.exists()) {
			throw new Exception(
					Messages
							.getString("TestDropFactory.folderStructureCreationFailedException")); 
		}
	}

	/**
	 * Returns given variant of file with given name
	 * 
	 * @param variant
	 *            used variant
	 * @param binaryName
	 *            binary file name
	 * @return destination file path, return null if the folder structure does
	 *         not exist.
	 */
	private IFile findDstFile(String variant, String binaryName) {
		IFolder variantFolder = selectedProject.getFolder(new Path(SLASH
				+ ROOT_FOLDER_NAME + SLASH + ATS_FOLDER + SLASH
				+ COMPONENTS_FOLDER + SLASH + variant));
		if (variantFolder.exists()) {
			return variantFolder.getFile(new Path(binaryName));
		} else
			return null;

	}

	/**
	 * Creates folder
	 * 
	 * @param folder
	 *            path
	 * @throws CoreException
	 *             if something goes wrong in folder creation
	 */
	private void createFolder(IFolder folder) throws CoreException {
		folder.create(false, true, null);

	}

	/**
	 * Packs given list of files into a zip archive
	 * 
	 * @param files
	 *            files to be packed into zip file
	 * @return zip file location
	 * @throws Exception
	 *             if zip file creation failed
	 */
	private String createZipFile(ArrayList<File> files) throws Exception {
		ZipOutputStream out = null;

		String zipFilePath = selectedProject.getLocation() + DOUBLDE_BACK_SLASH
				+ ROOT_FOLDER_NAME + DOUBLDE_BACK_SLASH + TESTDROP_ZIP_NAME;

		try {
			out = new ZipOutputStream(new FileOutputStream(zipFilePath));
			Iterator<File> iterator = files.iterator();
			byte[] buf = new byte[1024];

			while (iterator.hasNext()) {
				LogExceptionHandler.cancelIfNeed(progressMonitor);
				File file = (File) iterator.next();
				String path = file.getPath();
				path = path.substring(path.indexOf(ROOT_FOLDER_NAME)
						+ ROOT_FOLDER_NAME.length() + SLASH.length());
				FileInputStream in = new FileInputStream(file);
				out.putNextEntry(new ZipEntry(path));
				int len = 0;
				while ((len = in.read(buf)) > 0) {
					LogExceptionHandler.cancelIfNeed(progressMonitor);
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();

			}

		} catch (FileNotFoundException e) {
			LogExceptionHandler.log(e.getMessage());
		} catch (IOException e) {
			LogExceptionHandler.log(e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LogExceptionHandler.log(e.getMessage());
				}
			}
			File zipFile = new File(zipFilePath);
			if (!zipFile.exists()) {
				throw new FileNotFoundException(ZIP_FILE_CREATION_FAILED);
			}
		}
		return zipFilePath;
	}

	/**
	 * Resolves target and variant from the built binary file path
	 * 
	 * @param binarySrcPath
	 *            binary file path
	 * @return target and variant combination for inside the test drop
	 * @throws FileNotFoundException
	 *             if binary file is compiled for unsupported target device
	 * @throws IllegalArgumentException
	 *             if binarySrcPath is null
	 */
	private String resolveVariant(IPath binarySrcPath)
			throws FileNotFoundException {
		if (binarySrcPath == null) {
			throw new IllegalArgumentException(Messages
					.getString("TestDropFactory.srcPathCannotBeNull")); 
		}

		String[] binarySrcPathSegments = binarySrcPath.segments();
		String variant = null;

		// finds variant
		for (int i = 0; i < binarySrcPathSegments.length; i++) {
			LogExceptionHandler.cancelIfNeed(progressMonitor);
			for (int j = 0; j < targets.length; j++) {
				if (binarySrcPathSegments[i].equals(targets[j])) {
					if (binarySrcPathSegments[i + 1].equals(variatns[0])
							&& binarySrcPathSegments[i].equals(targets[0])
							|| binarySrcPathSegments[i + 1].equals(variatns[2])
							&& binarySrcPathSegments[i].equals(targets[2])) {
						variant = WINSCW_UDEB;
						break;
					} else if (binarySrcPathSegments[i + 1].equals(variatns[1])
							&& binarySrcPathSegments[i].equals(targets[0])
							|| binarySrcPathSegments[i + 1].equals(variatns[3])
							&& binarySrcPathSegments[i].equals(targets[2])) {
						variant = WINSCW_UREL;
						break;
					} else if (binarySrcPathSegments[i + 1].equals(variatns[0])
							&& binarySrcPathSegments[i].equals(targets[1])
							|| binarySrcPathSegments[i + 1].equals(variatns[2])
							&& binarySrcPathSegments[i].equals(targets[3])) {
						variant = ARMV5_UDEB;
						break;
					} else if (binarySrcPathSegments[i + 1].equals(variatns[1])
							&& binarySrcPathSegments[i].equals(targets[1])
							|| binarySrcPathSegments[i + 1].equals(variatns[3])
							&& binarySrcPathSegments[i].equals(targets[3])) {
						variant = ARMV5_UREL;
						break;
					} else {
						throw new FileNotFoundException(
								Messages
										.getString("TestDropFactory.binaryNotFoundException")); 
					}
				}
			}
		}
		return variant;
	}

	/**
	 * Creates test drop in zip format
	 * 
	 * @param testContent
	 *            content for creating a test drop
	 * @return path where the test drop is located
	 * @throws FileNotFoundException
	 *             if binary does not find
	 */
	private IPath createTestDropZip(TestContentValue testContent)
			throws FileNotFoundException, Exception {
		IPath binarySrcPath = testContent.getTestBinarySrcPath();
		String variant = testContent.getTestBinaryVariant();
		ArrayList<File> files = new ArrayList<File>();
		createFolderSkeleton(variant);
		if (needToFlashImages) {
			addImagesToDrop(testContent.getTargets(), files);
		}

		String testdropXMLPath = location + SLASH + ROOT_FOLDER_NAME + SLASH
				+ XML_FILE_NAME;
		testDropXMLCreator = new TestDropXMLCreator(testdropXMLPath);
		LogExceptionHandler.cancelIfNeed(progressMonitor);
		testDropXMLCreator.createTestDrop(testContent);
		
		//adding test module dll to test drop
		if (testContent.getTestType() != ModuleType.TESTCOMBINER) {
			File dstFile = findDstFile(variant,
					testContent.getTestBinarySrcPath().lastSegment()).getLocation()
					.toFile();
			if (dstFile == null) {
				throw new Exception(
						Messages
								.getString("TestDropFactory.destinationFileFoledDidNotFindException")); 
			}
			copyFile(binarySrcPath.toFile(), dstFile);
			files.add(new File(dstFile.toString()));
		}
		files.add(new File(testdropXMLPath));
		
		//adding script to test drop
		if (testContent.getTestType() == ModuleType.TESTCLASS
				|| testContent.getTestType() == ModuleType.TESTCOMBINER
				|| (testContent.getTestType() == ModuleType.NORMAL
						&& testContent.getTestScriptSrcPath() != null)) {
			Path scriptDstPath = new Path(selectedProject.getLocation() + SLASH
					+ ROOT_FOLDER_NAME + SLASH + ATS_FOLDER + SLASH
					+ COMPONENTS_FOLDER + SLASH + GENERAL_FOLDER + SLASH
					+ testContent.getTestScriptSrcPath().lastSegment());
			copyFile(testContent.getTestScriptSrcPath().toFile(), scriptDstPath
					.toFile());
			files.add(scriptDstPath.toFile());
		}
		
		//adding components for test combiner test drop
		if (testContent.getTestType() == ModuleType.TESTCOMBINER) {
			for (int i = 0; filesToInclude != null && i < filesToInclude.length; i++) {
				int indexOfLastSlash = testContent.getFilesToIncludeNames()[i].lastIndexOf("\\");
				String fileToIncludeName = testContent.getFilesToIncludeNames()[i].substring(indexOfLastSlash + 1);
				boolean isDll = false;
				if (fileToIncludeName.endsWith(".dll")) {
					isDll = true;
				}
				Path componentDstPath;
				if (isDll) {
					String componentDstPathString = selectedProject.getLocation() + SLASH
					+ ROOT_FOLDER_NAME + SLASH + ATS_FOLDER + SLASH
					+ COMPONENTS_FOLDER + SLASH;
					if (debugVariantOfTestCombinerActive) {
						componentDstPathString += ARMV5_UDEB;
					} else {
						componentDstPathString += ARMV5_UREL;
					}
					componentDstPathString += SLASH + fileToIncludeName;
					componentDstPath = new Path(componentDstPathString);
				}
				else {
					componentDstPath = new Path(selectedProject.getLocation() + SLASH
						+ ROOT_FOLDER_NAME + SLASH + ATS_FOLDER + SLASH
						+ COMPONENTS_FOLDER + SLASH + GENERAL_FOLDER + SLASH
						+ fileToIncludeName);
				}
				copyFile(new File(testContent.getFilesToIncludeNames()[i]), componentDstPath.toFile());
				files.add(componentDstPath.toFile());
			}
		}
		
		String zipFilePath = createZipFile(files);
		String method = StartUp.getConnectionProperties().getMethod();

		if (method.equals(HttpConnection.GET_METHOD)) {
			File zipFile = new File(zipFilePath);
			zipFilePath = StartUp.getConnectionProperties().getDropPath()
					.append(DOUBLDE_BACK_SLASH + zipFile.getName()).toFile()
					.toString();
			copyFile(zipFile, new File(zipFilePath));
		}

		return new Path(zipFilePath);
	}

	/**
	 * Resolves test binary location based on the project
	 * 
	 * @return if test binary found its path will be returned, otherwise returns null
	 */
	private IPath getBinarySrcPath() {

		ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
				.getProjectInfo(selectedProject);
		Iterator<IPath> iterator = EpocEngineHelper.getMMPFilesForProject(cpi)
				.iterator();
		while (iterator.hasNext()) {
			IPath mmpPath = (IPath) iterator.next();
			IPath binaryPath = EpocEngineHelper.getHostPathForExecutable(cpi
					.getDefaultConfiguration(), mmpPath);
			if (binaryPath.toFile().exists()) {
				return binaryPath;
			}
		}
		return null;

	}

	/**
	 * Resolves test script location based on the project
	 * 
	 * @return if test script found its path will be returned, otherwise returns null
	 * 
	 */
	private IPath getScriptSrcPath(ModuleType moduleType) {
		File confDir = new File(selectedProject.getLocation().toString() + "\\conf");

		if (confDir.isDirectory()) {
			File[] files = confDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				String name = files[i].getName();
				if (name.indexOf(".cfg") != -1) { 
					if ((moduleType == ModuleType.TESTCLASS && isTestScript(files[i]))
							|| moduleType == ModuleType.NORMAL) {
						return new Path(files[i].getPath());
					}
				}
			}
		}
		return null;
	}

	/**
	 * Checks that the script which is got from parameter keeps script file
	 * format
	 * 
	 * @param file
	 *            test script
	 * @return true if test script is in script file format, otherwise false
	 */
	private boolean isTestScript(File file) {
		BufferedReader in = null;
		StringBuffer buf = null;
		String str;
		boolean returnValue = false;
		try {
			in = new BufferedReader(new FileReader(file));
			buf = new StringBuffer();
			while ((str = in.readLine()) != null) {
				buf.append(str);
			}
			str = buf.toString();
			if (str.indexOf(SCRIPT_TEST_START) != -1
					&& str.indexOf(SCRIPT_TEST_END) != -1) {
				returnValue = true;
			}
		} catch (IOException e) {
			LogExceptionHandler.log(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LogExceptionHandler.log(e.getMessage());
				}
			}
		}
		return returnValue;
	}

	/**
	 * Adds image files to drop (for flashing)
	 * 
	 * @param targets
	 *            list of target devices
	 * @param zipFiles
	 *            adds target images into list of zip files
	 * @throws FileNotFoundException
	 *             if some file not found
	 * @throws CoreException
	 *             if something goes wrong in folder creation
	 */
	private void addImagesToDrop(List<TargetDeviceValue> targets,
			List<File> zipFiles) throws CoreException, FileNotFoundException {
		ArrayList<TargetDeviceValue> newTargets = new ArrayList<TargetDeviceValue>();
		Iterator<TargetDeviceValue> targetIterator = targets.iterator();
		Path imagesFolderPath = new Path(SLASH + ROOT_FOLDER_NAME + SLASH
				+ ATS_FOLDER + SLASH + IMAGES_FOLDER);
		IFolder imagesFolder = selectedProject.getFolder(imagesFolderPath);

		while (targetIterator.hasNext()) {
			LogExceptionHandler.cancelIfNeed(progressMonitor);
			TargetDeviceValue targetDeviceValue = (TargetDeviceValue) targetIterator
					.next();
			ArrayList<File> newLocations = new ArrayList<File>();
			if (targetDeviceValue.getImages() == null) {
				continue;
			}
			Iterator<File> imagesIterator = targetDeviceValue.getImages()
					.iterator();
			while (imagesIterator.hasNext()) {
				LogExceptionHandler.cancelIfNeed(progressMonitor);
				File image = (File) imagesIterator.next();
				if (!imagesFolder.exists()) {
					createFolder(imagesFolder);
				}
				File targetImage = selectedProject.getLocation().append(
						imagesFolderPath).append(
						new Path(SLASH + image.getName())).toFile();
				copyFile(image, targetImage);

				zipFiles.add(targetImage);
				String absolutePath = targetImage.getPath();
				absolutePath = absolutePath.substring(absolutePath
						.indexOf(ROOT_FOLDER_NAME)
						+ ROOT_FOLDER_NAME.length() + SLASH.length());
				newLocations.add(new File(absolutePath));
			}
			targetDeviceValue.setImagesInsideDrop(newLocations);
			newTargets.add(targetDeviceValue);

		}
		if (!newTargets.isEmpty()) {
			targets = newTargets;
		}
	}

	/**
	 * Returns component path
	 * 
	 * @return component path
	 */
	private String getComponentPath() {
		return ATS_FOLDER + DOUBLDE_BACK_SLASH + COMPONENTS_FOLDER
				+ DOUBLDE_BACK_SLASH;
	}

	/**
	 * Returns test script path
	 * 
	 * @return test script path
	 */
	private String getScriptPathInsideDrop() {
		return getComponentPath() + GENERAL_FOLDER + DOUBLDE_BACK_SLASH;
	}

	/**
	 * Removes test drop files from the local drive
	 * 
	 */
	public void removeTestDropFromLocalDrive() throws Exception {
		dispose();
		dropFolder = selectedProject.getFolder(ROOT_FOLDER_NAME);
		if (dropFolder.exists()) {
			deleteFromLocalDrive = true;
			new Thread(this).start();

		}
	}

	/**
	 * Makes ATS Test Drop package
	 * 
	 * @param masterDeviceValue
	 *            information of the selected master device
	 * @return IPath test drop location
	 * @throws Exception
	 *             if something goes wrong
	 */
	public IPath makeTestDrop(TargetDeviceValue masterDeviceValue) throws Exception {
			if (selectedCfgFile == null) {
				return makeTestDropForHardcodedUnitNormalTestClass(masterDeviceValue);
			}
			else {
				return makeTestDropForTestCombiner(masterDeviceValue);
			}
	}
	
	/**
	 * Makes a test drop for hardcoded, STIFUnit, normal and test class modules
	 * 
	 * @param masterDeviceValue
	 *            information of the selected master device
	 * @return test drop location
	 * @throws Exception
	 * 			if come incompatibilities are met. For example if the test module is of test class type
	 * 			and a cfg file is not provided
	 */
	private IPath makeTestDropForHardcodedUnitNormalTestClass(TargetDeviceValue masterDeviceValue)
		throws Exception {
			ArrayList<TargetDeviceValue> targets = new ArrayList<TargetDeviceValue>();
			targets.add(masterDeviceValue);

			ModuleType moduleType = null;
			try {
				moduleType = TestModuleTypeRecognizer.recognizeModuleType(selectedProject);
			} catch (NullPointerException ex) {
				return null;
			}
			
			IPath binarySrcPath = getBinarySrcPath();
			IPath scriptSrcPath = null;
			
			if (moduleType == ModuleType.TESTCLASS || moduleType == ModuleType.NORMAL) {
				scriptSrcPath = getScriptSrcPath(moduleType);
				if (moduleType == ModuleType.TESTCLASS && scriptSrcPath == null) {
					throw new Exception(
						Messages.getString("TestDropFactory.cannotResolveScriptFileLocationException")); 
				}
			}

			String targetDevicePlatform = masterDeviceValue
					.getValueFromPropertyList(PROPERTY_PLATFORM);
			String targetDeviceBuild = masterDeviceValue
					.getValueFromPropertyList(PROPERTY_BUILD);
			if (targetDevicePlatform == null || targetDeviceBuild == null) {
				throw new Exception(
						Messages
								.getString("TestDropFactory.cannotResolvePlatformTypeException")); 
			}
			String variant = null;

			try {
				variant = resolveVariant(binarySrcPath);
			} catch (IllegalArgumentException ex) {
				// do nothing yet, on the next step there will be thrown Exception
				ex.printStackTrace();
			}

			if (variant == null		//the proper version of dll was not compiled
					|| variant.indexOf(targetDevicePlatform.toLowerCase()) == -1
					|| variant.indexOf(targetDeviceBuild.toLowerCase()) == -1) {
				throw new Exception(
						Messages
								.getString("TestDropFactory.pleaseCompileNotify")); 
			}

			TestContentValue testContent = new TestContentValue(selectedProject.getName(), 
					null, null, targets, variant, binarySrcPath, scriptSrcPath,
					getComponentPath(), getScriptPathInsideDrop(), "STIF", 
					moduleType, StartUp.getTestResultPropertyValue()); 
			IPath testdropLoaction = createTestDropZip(testContent);

			return testdropLoaction;
	}
	
	/**
	 * Makes a test drop for test combiner.
	 * 
	 * @param masterDeviceValue
	 *            information of the selected master device
	 * @return path to the new test drop
	 * @throws Exception
	 * 			is thrown when some inconsistencies are met in masterDeviceValue
	 */
	private IPath makeTestDropForTestCombiner(TargetDeviceValue masterDeviceValue) 
		throws Exception {
		ArrayList<TargetDeviceValue> targets = new ArrayList<TargetDeviceValue>();
		targets.add(masterDeviceValue);

		ModuleType moduleType = ModuleType.TESTCOMBINER;

		String targetDevicePlatform = masterDeviceValue
				.getValueFromPropertyList(PROPERTY_PLATFORM);
		String targetDeviceBuild = masterDeviceValue
				.getValueFromPropertyList(PROPERTY_BUILD);
		if (targetDevicePlatform == null || targetDeviceBuild == null) {
			throw new Exception(
					Messages
							.getString("TestDropFactory.cannotResolvePlatformTypeException")); 
		}

		String variant = null;
		if (debugVariantOfTestCombinerActive){
			variant = debugVariant;
		} else {
			variant = releaseVariant;
		}
		IPath scriptSrcPath = selectedCfgFile.getLocation();
		TestContentValue testContent = new TestContentValue(selectedCfgFile.getProject().getName(), 
				selectedCfgFile.getName(), filesToInclude, targets, variant, null, scriptSrcPath,
				getComponentPath(), getScriptPathInsideDrop(), "STIF", 
				moduleType, StartUp.getTestResultPropertyValue()); 
		IPath testdropLoaction = createTestDropZip(testContent);

		return testdropLoaction;
	}

	/**
	 * Sends test drop to ATS Server
	 * 
	 * @param testdropLoaction
	 *            Test Drop location path
	 * @return test run id
	 * @throws Exception
	 *             if sending fails
	 */
	public int sendTestDrop(IPath testdropLoaction) throws Exception {
		ConnectionPropertyValue connectionPropertyValue = StartUp
				.getConnectionProperties();
		String method = connectionPropertyValue.getMethod();

		// Sends test drop to ATS Server
		LogExceptionHandler.cancelIfNeed(progressMonitor);
		HttpConnection connection = StartUp.getHttpConnection(testdropLoaction);
		return connection.sendTestDrop(method);
	}

	/**
	 * Sets progress monitor for cancellation job
	 * 
	 * @param progressMonitor
	 *            IProgressMonitor instance
	 */
	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
	}

	/**
	 * Gets test result from the ATS Server
	 * 
	 * @param testrun
	 *            test run id
	 * @param progressMonitor
	 *            progress monitor
	 * @return response from the ATS Server
	 * @throws IOException
	 */
	public static String getResultFromServer(int testrun,
			IProgressMonitor progressMonitor) throws IOException {
		HttpConnection conn = StartUp.getHttpConnection(null);
		String result = null;
		String reportType = "STIF_COMPONENT_REPORT_ALL_CASES"; 

		try {
			result = conn.getResultFromServer(testrun, reportType,
					progressMonitor);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}

		return result;
	}

	/**
	 * Used for deleting created files from the local drive
	 * after the run
	 */
	public void run() {
		if (deleteFromLocalDrive) {
			do {
				try {
					dropFolder.delete(true, null);
				} catch (CoreException ex) {
					ex.printStackTrace();
				}
			} while (dropFolder.exists());
		}

	}

}
