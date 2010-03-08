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

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SectionTag {

	//passing "section" node, create section object 
	//containig list of commands pressent in that section
	public SectionTag(Node list, String sectionName, String sectionEndName) {

		NodeList sectionNode = list.getChildNodes();
		this.name = sectionName;
		this.endName = sectionEndName;
		this.commandList = new ArrayList<Command>();

		for (int i = 0; i < sectionNode.getLength(); i++) {

			short typeC = sectionNode.item(i).getNodeType();
			if (typeC == ELEMENT_NODE) {

				Command text = new Command(sectionNode.item(i).getAttributes()
						.getNamedItem("id").getNodeValue(), sectionNode.item(i));
				commandList.add(text);
				
				
			}
		}
		
	}
	
	public String getName() {
		return name;

	}
	public String getEndName() {
		return endName;

	}
	
	public ArrayList<Command> getCommandList() {
		return commandList;

	}


	//begining of section (ex. [Test])
	private String name;
	
	//end of section (ex. [EndTest])
	private String endName;

	//list of all commands in section
	private ArrayList<Command> commandList;
	

}
