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

public class SelectiveArgument implements Arguments {



	public SelectiveArgument(Node selectionNode) {
		
		this.arglist = new ArrayList<Arguments>();
		
		NodeList selectionList = selectionNode.getChildNodes();

		for (int i = 0; i < selectionList.getLength(); i++) {
			short typeC = selectionList.item(i).getNodeType();
			if (typeC == ELEMENT_NODE) {
				String value = selectionList.item(i).getAttributes()
				.getNamedItem("value").getNodeValue();
				String textForNode = selectionList.item(i).getTextContent();
				String binding = selectionList.item(i).getAttributes()
						.getNamedItem("binding").getNodeValue();
				String nextvalue = selectionList.item(i).getAttributes()
						.getNamedItem("nextvalue").getNodeValue();
			
				Argument normal = new Argument(value,textForNode.trim(), binding,nextvalue);
				arglist.add(normal);
				
			}
		}
	}

	public String getNextValue() {
		return null;
	}
	
	public String getArgumentBinding(){
		return "none";
	}
	public String getArgumentName() {		
		return this.arglist.get(0).getArgumentName();
	}
	


	
	
	private ArrayList<Arguments> arglist;
	
	
	public ArrayList<String> validate(ArrayList<String> tokenizedLine) {
		int size = tokenizedLine.size();
		for(int i = 0 ; i < arglist.size() ; i++){
			
			tokenizedLine = this.arglist.get(i).validate(tokenizedLine);
			if(tokenizedLine.size() < size){
				return tokenizedLine;
			}
		}
		
		return tokenizedLine;
	}

	public String getArgumenType() {
		// TODO Auto-generated method stub
		return "value";
		
	}

}
