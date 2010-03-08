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

import org.w3c.dom.Node;




public class ArgumentFactory {
	
	public static Arguments createArgument(Node arg,int type) {
		switch (type){
		case 1:
			String value = arg.getAttributes().getNamedItem("value").getNodeValue();
			String textForNode = arg.getTextContent();
			String binding = arg.getAttributes()
					.getNamedItem("binding").getNodeValue();
			String nextvalue = arg.getAttributes()
					.getNamedItem("nextvalue").getNodeValue();
			
			Argument normal = new Argument(value,textForNode.trim(), binding,nextvalue);
			return normal;
			
		case 2:
			SelectiveArgument selective = new SelectiveArgument(arg);
			return selective;
			
		case 3:
			String cValue = arg.getAttributes().getNamedItem("value").getNodeValue();
			String cTextForNode = arg.getTextContent();
			String cBinding = arg.getAttributes()
					.getNamedItem("binding").getNodeValue();
			String cNextvalue = arg.getAttributes()
					.getNamedItem("nextvalue").getNodeValue();
			CreativeArgument crative = new CreativeArgument(cValue,cTextForNode.trim(), cBinding,cNextvalue);
			return crative;
			
		default:
			Argument none = new Argument(null,null,null,null);
			return none;
		}
	}
}
