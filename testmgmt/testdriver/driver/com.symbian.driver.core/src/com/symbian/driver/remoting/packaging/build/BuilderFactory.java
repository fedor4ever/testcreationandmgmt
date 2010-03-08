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

/**
 * Package builder factory.
 * 
 * @author EngineeringTools
 */
public class BuilderFactory {

	/**
	 * Standard Constructor
	 */
	protected BuilderFactory() {
		//  document or delete
	}

	/**
	 * Creates a new instance of this class.
	 * @return 
	 * 
	 */
	public static BuilderFactory newInstance() {
		return new BuilderFactory();
	}

	/**
	 * Creates and returns a new PackageBuilder or PackageEmulatorBuilder
	 * instance
	 * 
	 * @param aBuilderDesc
	 *            Either {@link PACKAGE} or {@link EMULATOR_IMAGE}
	 * @return a Builder.
	 */
	public Builder newBuilder(int aBuilderDesc) {
		Builder retBuild = null;

		switch (aBuilderDesc) {
		case Builder.PACKAGE: {
			retBuild = new PackageBuilder();
			break;
		}
		case Builder.EMULATOR_IMAGE: {
			retBuild = new PackageEmulatorBuilder();
			break;
		}

		}

		return retBuild;

	}

}
