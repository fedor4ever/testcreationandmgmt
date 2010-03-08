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

/**
 * 
Change .driver file format from

<?xml version="1.0" encoding="UTF-8"?>
<driver xmlns="http://www.symbian.com/TestDriver">
  <task name="xml" timeout="100000">
    <task name="te_TestServerSuite">
      <executeOnPC>
        <build URI="${sourceroot}\te_testserver\group\" testBuild="true">
          <componentName>te_testserversuite</componentName>
        </build>
      </executeOnPC>
      <task name="te_TestServerTest" timeout="10000">
        <transferToSymbian>
          <transfer PCPath="${sourceroot}\te_TestServer\testdata\te_TestServerSuite.ini" SymbianPath="c:\testdata\configs\te_TestServerSuite.ini" move="false"/>
        </transferToSymbian>
        <executeOnSymbian>
          <testExecuteScript PCPath="${sourceroot}\te_TestServer\scripts\te_TestServerSuite.script" SymbianPath="c:\te_TestServerSuite.script"/>
        </executeOnSymbian>
      </task>
    </task>
  </task>
</driver>

To 

<?xml version="1.0" encoding="UTF-8"?>
<driver:driver xmlns:driver="http://www.symbian.com/TestDriver">
  <task name="xml" timeout="100000">
    <task name="te_TestServerSuite">
      <executeOnPC>
        <build URI="${sourceroot}\te_testserver\group\" testBuild="true">
          <componentName>te_testserversuite</componentName>
        </build>
      </executeOnPC>
      <task name="te_TestServerTest" timeout="10000">
        <transferToSymbian>
          <transfer PCPath="${sourceroot}\te_TestServer\testdata\te_TestServerSuite.ini" SymbianPath="c:\testdata\configs\te_TestServerSuite.ini" move="false"/>
        </transferToSymbian>
        <executeOnSymbian>
          <testExecuteScript PCPath="${sourceroot}\te_TestServer\scripts\te_TestServerSuite.script" SymbianPath="c:\te_TestServerSuite.script"/>
        </executeOnSymbian>
      </task>
    </task>
  </task>
</driver:driver>

 *
 */
package com.symbian.driver.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DriverResourceConvertor {
	
	public static final String ROOT_ELEMENT = "driver:driver";
	
	public static final String XML_NAMESPACE_NAME = "xmlns:driver";
	
	public static final String XML_NAMESPACE_NAME_OLD = "xmlns";
	
	public static final String XML_NAMESPACE_VALUE = "http://www.symbian.com/TestDriver";

	/**
	 * Clone node from n1 to n2. Only clone sub nodes and sub node's attribute. 
	 * 
	 * @param d1 original Document
	 * @param n1 original Node
	 * @param d2 target Document
	 * @param n2 target Node
	 */
	private static void clone(Document d1, Node n1, Document d2, Node n2){
		
		NodeList children = n1.getChildNodes();
		for(int i=0; i<children.getLength(); i++){
			Node child = children.item(i);
			
			if(child instanceof Element){
				Element element = d2.createElement(child.getNodeName());
				n2.appendChild(element);
				
				NamedNodeMap attributes = child.getAttributes();
				if(attributes != null){
					for(int j=0; j<attributes.getLength(); j++){
						Node attribute = attributes.item(j);
						element.setAttribute(attribute.getNodeName(), attribute.getNodeValue());
					}
				}
				
				clone(d1, child, d2, element);
			}
			else if(child instanceof Comment){
				Comment comment = d2.createComment(((Comment)child).getData());
				n2.appendChild(comment);
			}
			else if(child instanceof Text){
				Text text = d2.createTextNode(((Text)child).getData());
				n2.appendChild(text);
			}
		}
	}
	
	/**
	 * invoked for DriverEditor and Carbide
	 * 
	 * @param uri
	 * @throws Exception
	 */
	public static void convertResourceForEditor(URI uri) throws Exception {
		
		File file = new File(Platform.getLocation().toOSString(), uri.toPlatformString(false));
		File fileBackup = new File(file.getParent(), file.getName() + ".bak");
		
		if(fileBackup.exists()){
			int i = 1;
			String filename = fileBackup.getName();
			while(fileBackup.exists()){
				fileBackup = new File(file.getParentFile(), filename + "." + i++);
			}
		}
		
		file.renameTo(fileBackup);
		
		convertResource(fileBackup, file);
	}

	/**
	 * invoked for Loader
	 * 
	 * @param uri
	 * @throws Exception
	 */
	public static void convertResourceForLoader(URI uri) throws Exception {

		File file = new File(uri.devicePath());
		File fileBackup = new File(file + ".bak");
		
		if(fileBackup.exists()){
			int i = 1;
			String filename = fileBackup.getName();
			while(fileBackup.exists()){
				fileBackup = new File(file.getParentFile(), filename + "." + i++);
			}
		}
		
		file.renameTo(fileBackup);
		
		convertResource(fileBackup, file);
	}
	
	/**
	 * change <driver xmlns="http://www.symbian.com/TestDriver"> 
	 * to <driver:driver xmlns:driver="http://www.symbian.com/TestDriver">
	 * 
	 * @param oldFile
	 * @param newFile
	 * @throws Exception
	 */
	private static void convertResource (File oldFile, File newFile) throws Exception {
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(oldFile);

		Element element = document.getDocumentElement();

		Document newDocument = builder.newDocument();
		Element root = newDocument.createElement(ROOT_ELEMENT);
		
		// All attribute reserved, except "xmlns". 
		NamedNodeMap attributes = element.getAttributes();
		if(attributes != null) {
			for(int i=0; i<attributes.getLength(); i++){
				Node attribute = attributes.item(i);
				if(XML_NAMESPACE_NAME_OLD.equals(attribute.getNodeName())){
					continue;
				}
				else{
					root.setAttribute(attribute.getNodeName(), attribute.getNodeValue());
				}
			}
		}
		// Change "xmlns" to "xmlns:driver"
		root.setAttribute(XML_NAMESPACE_NAME, XML_NAMESPACE_VALUE);
		
		newDocument.appendChild(root);
				
		clone(document, element, newDocument, root);

		DOMSource in2=new DOMSource(newDocument);
		StreamResult outs=new StreamResult(newFile);
		Transformer transformer=TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(in2,outs);
	}
	
	/**
	 * For convert test only. 
	 * 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		URI uri = URI.createFileURI("M:\\te_TestServer\\xml\\xml.driver");
		convertResourceForLoader(uri);
	}
}
