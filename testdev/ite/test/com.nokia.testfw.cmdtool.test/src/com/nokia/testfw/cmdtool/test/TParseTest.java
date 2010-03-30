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
* Description: Unit test for cmdtool.
*              6 cases included in the test.
*              1, standard project test: a mmp file in a bld.inf file.
*              2, multi-mmp files in a bld.inf file.
*              3, multi bld.inf files in a bld.inf file.
*              4, error command
*              5, help command
*              6, version command              
*
*/
package com.nokia.testfw.cmdtool.test;

import java.util.Map;

import com.nokia.testfw.cmdtool.ui.Executor;
import com.nokia.testfw.cmdtool.util.CmdExecutor;
import com.nokia.testfw.cmdtool.util.PathUtil;
import com.nokia.testfw.codegen.model.ProjectNodeImpl;

import junit.framework.TestCase;

public class TParseTest extends TestCase {
	
	/*NOTICE:
	Before Start your test.
	please modify these two lines to locate your testing resource and test tool.
	*/
	//Location of testing resource.
	private String testcaseresourcelocation = "z:\\unittest\\";
	//location of SymUTCreator.bat
	private String symutcreatorlocation = "D:\\TestTools\\project\\STF\\2.development";
	
	private String case1filename = "standard\\testThread\\group\\bld.inf";
	private String case1prjname = "testThread";
	private String case1basepath = "standard\\testThread";
	private int case1libnumber = 1;
	private int case1userincnumber = 1;
	private int case1sysincnumber = 1;
	private int case1classnumber = 2;
	
	private String case2filename = "several.mmp.in.one.path\\SysLibs\\ECom\\bld.inf";
	private String case2prjname = "SysLibs";
	private String case2basepath = "several.mmp.in.one.path\\SysLibs";
	private int case2libnumber = 2;
	private int case2userincnumber = 4;
	private int case2sysincnumber = 4;
	private int case2classnumber = 4;
	
	private String case3filename = "several.bld.inf\\multiprj\\bld.inf";
	private String case3prjname = "multiprj";
	private String case3basepath = "several.bld.inf\\multiprj";
	private int case3libnumber = 1;
	private int case3userincnumber = 2;
	private int case3sysincnumber = 1;
	private int case3classnumber = 2;
	
	public TParseTest()
	{}
	
	public void testCase1_standard()
	{
		Testing(case1filename, 
				case1prjname, 
				case1basepath, 
				case1libnumber,
				case1userincnumber,
				case1sysincnumber,
				case1classnumber);
	}
	
	public void testCase2_multiMMP()
	{
		Testing(case2filename, 
				case2prjname, 
				case2basepath, 
				case2libnumber,
				case2userincnumber,
				case2sysincnumber,
				case2classnumber);
	}
	
	public void testCase3_multiBLDINF()
	{
		Testing(case3filename, 
				case3prjname, 
				case3basepath, 
				case3libnumber,
				case3userincnumber,
				case3sysincnumber,
				case3classnumber);
	}
	
	public void testCase4_ErrorCommand()
	{
		String exe = PathUtil.Combine(symutcreatorlocation, "SymUTCreator");
		String result = CmdExecutor.Execute(exe + " -x", symutcreatorlocation);
		assertTrue("User input a error command!", result.startsWith("ERROR"));				
	}
	
	public void testCase5_HelpCommand()
	{
		String exe = PathUtil.Combine(symutcreatorlocation, "SymUTCreator");
		String result = CmdExecutor.Execute(exe + " -?", symutcreatorlocation);
		assertFalse("User input a correct command!", result.startsWith("ERROR"));				
	}
	
	public void testCase6_VersionCommand()
	{
		String exe = PathUtil.Combine(symutcreatorlocation, "SymUTCreator");
		String result = CmdExecutor.Execute(exe + " -v", symutcreatorlocation);
		assertFalse("User input a correct command!", result.startsWith("ERROR"));				
	}
	
	
	
	
	private void Testing(String filename, String prjname, String basepath, int libnumber, int userincnumber, int sysincnumber, int classnumber)
	{
		Executor executor = new Executor();
		Map<String, Object> data  = executor.Parse(PathUtil.Combine(testcaseresourcelocation, filename));
		assertEquals(data.get("project_name").toString(), prjname);
		assertEquals(data.get("basepath").toString().toLowerCase(), PathUtil.Combine(testcaseresourcelocation,basepath).toLowerCase());
		ProjectNodeImpl projectNode = (ProjectNodeImpl)data.get("project_object");
		assertEquals(libnumber, projectNode.getLibrarys().size());
		assertEquals(userincnumber, projectNode.getUserIncludes().size());
		assertEquals(sysincnumber, projectNode.getSystemIncludes().size());
		assertEquals(classnumber, projectNode.getChildren().size());
		String target = PathUtil.Combine(data.get("basepath").toString(), "unittest");
		executor.RunCodegen(data, target);
		assertTrue("Code generated failed!", PathUtil.Exist(target));
		target = PathUtil.Combine(target, "group");
		Compile(target);
		String epoc32root = getEPOC32Path(target);
		assertTrue("EPOC32 path not exist!",!epoc32root.equals(""));
		target = PathUtil.Combine(epoc32root, "\\release\\winscw\\udeb\\ut_" + prjname + ".dll");
		assertTrue("Code compiled failed!", PathUtil.Exist(target));
		
		
	}
	
	private String getEPOC32Path(String path)
	{
		String epocroot = CmdExecutor.Execute("set EPOCROOT", path);
		if(epocroot.length() < 10)
		{
			return "";			
		}
		// EPOCROOT=\
		epocroot = epocroot.substring(9);
		
		
		if(epocroot.startsWith("\\"))
		{
			//relative path
			epocroot = path.substring(0, 2) + epocroot;  // drive path.
			epocroot += "epoc32";
		}
		else
		{
			//absolute path
			epocroot = PathUtil.Combine(epocroot, "epoc32");			
		}
		return epocroot;
		
	}
	
	private void Compile(String path)
	{
		CmdExecutor.Execute("bldmake clean", path);
		CmdExecutor.Execute("bldmake bldfiles", path);
		CmdExecutor.Execute("abld test clean winscw udeb", path);
		CmdExecutor.Execute("abld test build winscw udeb", path);
		CmdExecutor.Execute("bldmake clean", path);
	}
	
	public static void main(String[] args) 
	{}
}
