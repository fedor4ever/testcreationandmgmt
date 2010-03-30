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
* Description: wrapper class for manual test.
*
*/
package com.nokia.testfw.cmdtool.test;

import java.util.Vector;

import com.nokia.testfw.cmdtool.cbrowserwrapper.*;

public class GccxmlWrapperTest implements IParseListener {
	
	
	public GccxmlWrapperTest()
	{
	}
	
	public void Run(String bldfilename)
	{
		IParseWrapper wrapper = new CBrowserWrapper();
		wrapper.AddParseListener(this);
		Vector<SymCMMPInfo> mmpList = wrapper.Parse(bldfilename);		
		for(int i=0;i<mmpList.size();i++)
		{
			SymCMMPInfo mmpInfo = mmpList.get(i);
			System.out.println("======================================");
			System.out.println("MMP FILE:" + mmpInfo.Name);

			for(SymCClassInfo classInfo: mmpInfo.ClassList())
			{
				System.out.println("      Class:" + classInfo.Name);
				
				for(SymCMethodInfo methodInfo : classInfo.FunctionList())
				{
					System.out.print("            " + methodInfo.toString() + "\r\n");	
						System.out.print("                >>>md: " + methodInfo.DefinitionFile + "\r\n");
						System.out.print("                >>>mi: " + methodInfo.ImplementationFile + "\r\n");
				}
			}
			
		}
		
		System.out.println("**********************************************************");
		
	}
	
	public void GetParseMessage(ParseEventType type, String message) {
		// TODO Auto-generated method stub
		if(type == ParseEventType.Done)
		{
			System.out.println("Done!");			
		}
		else if(type == ParseEventType.Failed)
		{
			System.out.println("Error: " + message);			
		}
		else
		{
			System.out.println(message);			
		}
	}
}
