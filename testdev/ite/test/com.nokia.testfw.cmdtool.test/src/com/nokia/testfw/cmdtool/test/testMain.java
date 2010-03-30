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
* Description: Console based testing portal.
*
*/
package com.nokia.testfw.cmdtool.test;

import java.io.IOException;

public class testMain {
	public static void main(String args[])
	{
		if(args.length != 1)
		{
			System.out.println("Please input a invalid bld file path");
			return;
		}
		
		try {
			GccxmlWrapperTest tester = new GccxmlWrapperTest();
			tester.Run(args[0]);
			System.out.println("test done! Press any key to exit.");
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
