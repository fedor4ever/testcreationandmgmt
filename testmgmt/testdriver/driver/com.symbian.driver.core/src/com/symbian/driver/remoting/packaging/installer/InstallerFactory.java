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



package com.symbian.driver.remoting.packaging.installer;

/**
 * Installer factory
 * 
 * @author EngineeringTools
 */
public class InstallerFactory {

	/**
	 * Standard Constructor
	 */
	protected InstallerFactory() {
		//  document or delete
	}

	/**
	 * create and return a new Installerfactory instance.
	 * 
	 * @return a new InstallerFactory instance.
	 */
	public static InstallerFactory newInstance() {
		return new InstallerFactory();
	}

	/**
	 * create and return a new Installer instance.
	 * 
	 * @param aInstallerDesc
	 *            int : an installer description (Installer.PACKAGE)
	 * @return the new Installer
	 */
	public Installer newInstaller(int aInstallerDesc) {
		Installer lRetInstaller = null;

		switch (aInstallerDesc) {
		case Installer.PACKAGE: {
			lRetInstaller = new PackageInstaller();
			break;
		}

		}

		return lRetInstaller;

	}

}
