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
package com.nokia.testfw.codegen;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.nokia.testfw.codegen.model.ClassNodeImpl;
import com.nokia.testfw.codegen.model.MethodNodeImpl;
import com.nokia.testfw.codegen.model.ProjectNodeImpl;
import com.nokia.testfw.codegen.templates.CMDTemplateBuilder;
import com.nokia.testfw.codegen.templates.TemplateBuilderFactory;

import junit.framework.TestCase;

public class CodegenEngineTest extends TestCase {

	CodegenEngine ce;
	CodegenEngine ge;
	
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		System.setProperty(TemplateBuilderFactory.TEMPLATEBUILDERCLASS,
				CMDTemplateBuilder.class.getName());

		Map<String, Comparable> data = new HashMap<String, Comparable>();
		ProjectNodeImpl lProjectNode = new ProjectNodeImpl("TestModule");  //test_project
		data.put("project_name", "TestModule");  //test_project
		data.put("project_object", lProjectNode);
		data.put("suite_name", "TestSuite");
		data.put("location", "test");
		data.put("target_type", "EXE");
		data.put("UID3", "0x10000001");
		data.put("auther_name", "kevin");
		data.put("copyright", "");

		ClassNodeImpl lClassNode = new ClassNodeImpl("testClass", lProjectNode);
		lClassNode.setDeclLocation("C:\\testHeader.h");
		Set<String> set = new TreeSet<String>();
		set.add("C:\\testCpp.cpp");
		lClassNode.setImplLocation(set);
		MethodNodeImpl lMethodNode = new MethodNodeImpl("testMethod",
				lClassNode);
		lMethodNode.addParameters("String", "param1");
		lMethodNode.addParameters("String", "param2");
		lClassNode.addChild(lMethodNode);
		lProjectNode.addChild(lClassNode);

		ce = new CodegenEngine("C:\\Temp\\STF_hardcode", data);
		
		Map<String, Comparable> data1 = new HashMap<String, Comparable>();
		ProjectNodeImpl lProjectNode1 = new ProjectNodeImpl("TestModule");  //test_project
		data1.put("project_name", "TestModule");  //test_project
		data1.put("project_object", lProjectNode1);
		data1.put("suite_name", "TestSuite");
		data1.put("location", "test");
		data1.put("target_type", "EXE");
		data1.put("UID3", "0x10000001");
		data1.put("auther_name", "kevin");
		data1.put("copyright", "");

		ClassNodeImpl lClassNode1 = new ClassNodeImpl("testClass", lProjectNode1);
		lClassNode1.setDeclLocation("C:\\testHeader.h");
		Set<String> set1 = new TreeSet<String>();
		set1.add("C:\\testCpp.cpp");
		lClassNode1.setImplLocation(set1);
		MethodNodeImpl lMethodNode1 = new MethodNodeImpl("testMethod",
				lClassNode1);
		lMethodNode1.addParameters("String", "param1");
		lMethodNode1.addParameters("String", "param2");
		lClassNode1.addChild(lMethodNode1);
		lProjectNode1.addChild(lClassNode1);

		ge = new CodegenEngine("C:\\Temp\\STF_Script", data1);
	}

	public void testGenerate() {
		try {
			ce.init();
			ge.init();
			assertTrue(ce.isInit);
		} catch (Exception e) {
			fail("CodegenEngine init failed.");
		}
		try {
			ce.generate("STF_hardcode");
			ge.generate("STF_Script");
		} catch (Exception e) {
			e.printStackTrace();
			fail("CodegenEngine generation failed.");
		}
//		try {
//			ce.generate("SymbianUnitTest");
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("CodegenEngine generation failed.");
//		}
	}

}
