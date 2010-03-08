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

package com.nokia.s60tools.stif.scripteditor.utils;

import static org.w3c.dom.Node.ELEMENT_NODE;

import java.util.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Command {

	//conscructor of command class creates lists of mandatory and optional arguments 
	public Command(String name, Node argnode) {

		this.commandName = name;
		this.mandatory = new ArrayList<Arguments>();
		this.optional = new ArrayList<Arguments>();

		NodeList list = argnode.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			short typeC = list.item(i).getNodeType();
			if (typeC == ELEMENT_NODE) {

				if (list.item(i).getNodeName() == "mandatory") {

					createArgumentsList(list.item(i), mandatory);
				}
				if (list.item(i).getNodeName() == "optional") {

					createArgumentsList(list.item(i), optional);
				}				
				if (list.item(i).getNodeName().equals("errornote")){
					
					this.errorNote = list.item(i).getTextContent().trim();
				
				}
				if (list.item(i).getNodeName().equals("commandinfo")){
					
					this.commandInfo  = list.item(i).getTextContent().trim();
				
				}
			
			}

		}
	}
	
	public String getCommandName(){
		return commandName;
	}

	private void createArgumentsList(Node argNode, ArrayList<Arguments> arglist) {

							
		NodeList argNodes = argNode.getChildNodes();

		for (int i = 0; i < argNodes.getLength(); i++) {
			short typeC = argNodes.item(i).getNodeType();
			if (typeC == ELEMENT_NODE) {
				
				String type = argNodes.item(i).getAttributes()
				.getNamedItem("type").getNodeValue();
				
				if (type.equals("normal")){ 
					arglist.add(ArgumentFactory.createArgument(argNodes.item(i),1));
				}
				if (type.equals("selective")){
					arglist.add(ArgumentFactory.createArgument(argNodes.item(i),2));
					
				}
				if (type.equals("creative")){
					arglist.add(ArgumentFactory.createArgument(argNodes.item(i),3));
					
				}

				
			}
			
		}
		
	}

	public String toString() {
		String command = ("command name: " + commandName +errorNote
				+ "\nnumber of mandatory args: " + mandatory.size()
				+ "number of opcional args: " + optional.size());
		return command;
	}
	
	public ArrayList<Arguments> getMandatory(){
		return mandatory;
	}
	
	public ArrayList<Arguments> getOptional(){
		return optional;
	}
	
	public int getMandatorySize(){
		int size = 0;
		
		for (int i = 0 ; i < this.mandatory.size() ; i++){
			if(this.mandatory.get(i).getArgumentBinding().equals("none") ){
				size++;
			}
			else if(this.mandatory.get(i).getArgumentBinding().equals("=") ){
				size=size+3;
			}
		}
	
		return size;
	}
	
	public String getErrorNote(){
		return errorNote;
	}
	public String getCommandInfo(){
		return commandInfo;
	}
	//
	private String commandName;
	
	private String errorNote;
	
	private String commandInfo;

	// list containing mandatory arguments
	private ArrayList<Arguments> mandatory;

	// list containing optional arguments
	private ArrayList<Arguments> optional;

}