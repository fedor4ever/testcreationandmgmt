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

package com.nokia.testfw.stf.scripteditor.utils;

import static org.w3c.dom.Node.ELEMENT_NODE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser{

	/**
	 * Creates parser object based on location of xml file 
	 * @param xmlLocation
	 */
	public Parser(String xmlLocation) {

		this.sectionList = new ArrayList<SectionTag>();

		InputStream is = getClass().getResourceAsStream(xmlLocation);
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setNamespaceAware(true);
		builderFactory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document xmlDoc = null;

		try {

			xmlDoc = builder.parse(is);

		} catch (SAXException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Node rootNode = xmlDoc.getDocumentElement();

		NodeList list = rootNode.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {

			short typeC = list.item(i).getNodeType();
			if (typeC == ELEMENT_NODE) {

				Node sectionNode = list.item(i);
				SectionTag section = new SectionTag(list.item(i), 
						sectionNode.getAttributes().getNamedItem("name").getNodeValue(),
						sectionNode.getAttributes().getNamedItem("endname").getNodeValue(),
						sectionNode.getAttributes().getNamedItem("proposal").getNodeValue());
				sectionList.add(section);
			}
		}
	}
	
	/**
	 * Returns list of parsed sections 
	 */
	public ArrayList<SectionTag> gerSection() {
		return sectionList;
	}
	
	public SectionTag getSectionByName(String sectionName) {
		for (SectionTag tag : sectionList) {
			if (tag.getName().equals(sectionName)) {
				return tag;
			}
		}
		return null;
	}

	/**
	 * Returns list of commands from all section
	 * 
	 * @param Parser object
	 * @return ArrayList of strings - List of commands
	 * 	  
	 */
	public ArrayList<String> provideCommands() {

		ArrayList<SectionTag> arrayOfSections = this.gerSection();
		String[] sectionkeywrds = new String[arrayOfSections.size()];
		ArrayList<String> keywordsList = new ArrayList<String>();
		for (int i = 0; i < sectionkeywrds.length; i++) {

			SectionTag section = arrayOfSections.get(i);
			for (int g = 0; g < section.getCommandList().size(); g++) {

				String command = section.getCommandList().get(g)
						.getCommandName();
				keywordsList.add(command);
			}
		}
		return keywordsList;
	}

	private ArrayList<SectionTag> sectionList;

	/**
	 * first tier of tokenized line checking, gets proper command object type, and invokes mandatory argument checking 
	 * 
	 * @param tokenizedLine
	 * @param commandName
	 * @return
	 */

	public String checkArguments(ArrayList<String> tokenizedLine,
			String commandName) {

		ArrayList<SectionTag> arrayOfSections = this.gerSection();
		String[] sectionkeywrds = new String[arrayOfSections.size()];

		for (int i = 0; i < sectionkeywrds.length; i++) {

			SectionTag section = arrayOfSections.get(i);
			for (int g = 0; g < section.getCommandList().size(); g++) {

				String command = section.getCommandList().get(g)
						.getCommandName();
				if (command.equals(commandName)) {
					boolean ret = checkMandatory(section.getCommandList()
							.get(g), tokenizedLine);
					if (!ret) {
						return section.getCommandList().get(g).getErrorNote();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Checks mandatory arguments and invokes checking of optional arguments if needed
	 * 
	 * @param command - reference command parsed from XML file
	 * @param tokenizedLine - list containig arguments from editor (without command token)
	 * @return
	 */

	private boolean checkMandatory(Command command,	ArrayList<String> tokenizedLine) {
		ArrayList<String> ret = null;
		boolean optionalCheck;
		ArrayList<String> tokenizedMandatory = new ArrayList<String>();
		ArrayList<Arguments> argsList = command.getMandatory();
		ArrayList<Arguments> opionalList = command.getOptional();
		ArrayList<String> tokenizedOptional = new ArrayList<String>(
				tokenizedLine);

		int size = command.getMandatorySize();
		
		// conditions checked if command can not have arguments (ex. endloop)
		if(argsList.size()==0 && opionalList.size() == 0){
			if(tokenizedLine.size() ==0){
			return true;
			} else return false;
		}
		
		if (size > tokenizedLine.size()) {
			return false;
		}
		if (!tokenizedLine.isEmpty()) {
			for (int g = 0; g < tokenizedLine.size() && g < size; g++) {
				tokenizedMandatory.add(tokenizedLine.get(g));
				tokenizedOptional.remove(tokenizedLine.get(g));
			}
		}

		for (int i = 0; i < argsList.size(); i++) {

			ret = command.getMandatory().get(i).validate(tokenizedMandatory);
		}
		if (ret.size() == 0) {
			if (tokenizedOptional.size() == 0) {
				return true;
			}
			optionalCheck = checkOptional(opionalList, tokenizedOptional);
			if (optionalCheck) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks optional arguments
	 * 	  
	 * @param opionalList - list of optional arguments parsed from XML
	 * @param optionalArgs - list of tokens remain after checking mandatory arguments 
	 * @return true if command contains valid optional arguments
	 */
	private boolean checkOptional(ArrayList<Arguments> optionalList,
			ArrayList<String> optionalArgs) {
		
		ArrayList<Arguments> tempOptionalList = new ArrayList<Arguments>(optionalList);

		for (int i = 0; i < tempOptionalList.size();) {
			int t = optionalArgs.size();
			optionalArgs = tempOptionalList.get(i).validate(optionalArgs);
			if (optionalArgs.size() != t) {
				tempOptionalList.remove(i);
				i = 0;
				continue;
			}
			i++;
		}
		if (optionalArgs.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return list of section tags
	 */
	public String[] provideSectionTags() {
		ArrayList<SectionTag> arrayOfSections = gerSection();
		String[] sectionkeywrds = new String[(arrayOfSections.size()*2)];
		int j = 0;
		for(int i = 0 ; (i < sectionkeywrds.length-1 && j < sectionkeywrds.length-1) ; i++ ){
			
			sectionkeywrds[j] = ("[" +arrayOfSections.get(i).getName() +"]");
			sectionkeywrds[++j] = ("[" +arrayOfSections.get(i).getEndName() +"]");
			j++;
			
		}
		return sectionkeywrds;	
	}
	public static ArrayList<String> provideMacrosObjects() {
		// TODO Auto-generated method stub
		return createdMacrosList;
	}
	
	public static ArrayList<String> provideObjectsList() {
		// TODO Auto-generated method stub
		return createdObjectsList;
	}
	
	public static ArrayList<String> createdMacrosList = new ArrayList<String>();
	public static ArrayList<String> createdObjectsList = new ArrayList<String>();
		
	
		
	
	
}
