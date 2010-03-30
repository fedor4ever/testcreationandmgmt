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
/**
 * 
 */
package com.nokia.testfw.launch;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import com.nokia.testfw.resultview.model.IDataProcessor;

/**
 * @author k21wang
 * 
 */
public class ProcessorRegistry {

	private static final String ID_EXTENSION_POINT_TEST_PROCESSORS = TFWLaunchPlugin.PLUGIN_ID
			+ ".testRunProcessors";
	private static ProcessorRegistry iRegistry;

	private final IExtensionPoint iExtensionPoint;

	public static ProcessorRegistry getDefault() {
		if (iRegistry != null)
			return iRegistry;

		iRegistry = new ProcessorRegistry(Platform.getExtensionRegistry()
				.getExtensionPoint(ID_EXTENSION_POINT_TEST_PROCESSORS));
		return iRegistry;
	}

	/**
	 * @param extensionPoint
	 * 
	 */
	private ProcessorRegistry(IExtensionPoint extensionPoint) {
		iExtensionPoint = extensionPoint;
	}

	public IDataProcessor[] getAllProcessors() {
		ArrayList<IDataProcessor> list = new ArrayList<IDataProcessor>();
		IExtension[] extensions = iExtensionPoint.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configs = extension
					.getConfigurationElements();
			for (IConfigurationElement config : configs) {
				Object testRunProcessor;
				try {
					testRunProcessor = config
							.createExecutableExtension("class");
					if (testRunProcessor instanceof IDataProcessor) {
						list.add((IDataProcessor) testRunProcessor);
					}
				} catch (CoreException e) {
					TFWLaunchPlugin.log(e);
				} //$NON-NLS-1$
			}
		}
		return list.toArray(new IDataProcessor[0]);
	}
}
