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


package com.nokia.s60tools.stif.configmanager;

/**
 * Measurment modules type
 *
 */
public enum MeasurementModule { NOT_DEFINED, UNKNOWN,
	/**
	 * Do not disable any measurment module plugin
	 */
	STIF_MEASUREMENT_DISABLE_NONE,
	/**
	 * Disable all measurment module plugins
	 */
	STIF_MEASUREMENT_DISABLE_ALL, 
	/**
	 * Disable measurment plugin 01
	 */
	STIF_MEASUREMENT_PLUGIN_01,
	/**
	 * Disable measurment plugin 02
	 */
	STIF_MEASUREMENT_PLUGIN_02,
	/**
	 * Disable measurment plugin 03
	 */
	STIF_MEASUREMENT_PLUGIN_03,
	/**
	 * Disable measurment plugin 04
	 */
	STIF_MEASUREMENT_PLUGIN_04,
	/**
	 * Disable measurment plugin 05
	 */
	STIF_MEASUREMENT_PLUGIN_05,
	/**
	 * Disable bappea profiler measurment module plugin
	 */
	STIF_BAPPEA_PROFILER }
