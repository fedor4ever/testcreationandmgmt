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


package com.nokia.s60tools.stif.configeditor.editors;

import java.util.ArrayList;
import java.util.List;

import com.nokia.s60tools.stif.configmanager.SectionElementType;

/**
 * Modules tree node
 * 
 */
public class ModulesTreeNode {
	/**
	 * Node type
	 */
	private SectionElementType type;
	/**
	 * Node value
	 */
	private String value;
	/**
	 * Index in config source. 
	 * 
	 * Depens on node <code>type</code> it represents module index in config 
	 * file or testcase file entry index in <code>[New_Module]...[End_Module]</code> 
	 * section of config file.
	 */
	private int index;
	/**
	 * Parent node
	 */
	ModulesTreeNode parent = null;
	/**
	 * List of node childrens
	 */
	private List<ModulesTreeNode> childrens = null; 	
	/**
	 * Creates node
	 */
	public ModulesTreeNode() {
		childrens = new ArrayList<ModulesTreeNode>();
	}
	
	/**
	 * Creates node
	 * @param parent parent node
	 */
	public ModulesTreeNode(ModulesTreeNode parent) {
		childrens = new ArrayList<ModulesTreeNode>();
		if ( parent != null ) {
			this.parent = parent;
			parent.childrens.add(this);
		}
	}

	/**
	 * Gets node childrens
	 * @return list of node childrens
	 */
	public List<ModulesTreeNode> getChildrens() {		
		return childrens;
	}
	
	/**
	 * Adds child node
	 * @param node child node to add
	 */
	public void addChildren( ModulesTreeNode node ) {
		if ( node != null ) {
			node.parent = this;
			childrens.add( node );			
		}
	}
	
	/**
	 * Removes child node
	 * @param node node to remove
	 */
	public void removeChildrean( ModulesTreeNode node ) {
		childrens.remove( node );
		node.parent = null;
	}
	
	/**
	 * Disposes node
	 */
	public void dispose() {
		if ( parent != null ) {
			parent.childrens.remove(this);
			this.parent = null;
		}
	}

	/**
	 * Gets index value
	 * @return index value
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets new index value
	 * @param index new index value
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets parent node
	 * @return parent node
	 */
	public ModulesTreeNode getParent() {
		return parent;
	}

	/**
	 * Sets new parent node
	 * @param parent parent node
	 */
	public void setParent( ModulesTreeNode parent ) {
		if ( parent != null ) {
			this.parent = parent;
			parent.childrens.add( this );
		}
	}	
	
	/**
	 * Gets node type
	 * @return node type
	 */
	public SectionElementType getType() {
		return type;
	}

	/**
	 * Sets new node type value
	 * @param type new node type value
	 */
	public void setType(SectionElementType type) {
		this.type = type;
	}

	/**
	 * Gets node value
	 * @return node value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets new node value
	 * @param value new node value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Checks if node has childreans
	 * @return <b><code>true</code></b> if node has childrens, <b><code>false</code></b>
	 * 			if node does not have childrens  
	 */
	public boolean hasChildren() {
		if ( ( childrens != null ) && ( childrens.size() != 0 ) ) {
			return true;
		}
		return false;
	}

	/**
	 * Removes all children nodes
	 */
	public void removeAllChildreans() {
		childrens = new ArrayList<ModulesTreeNode>();
	}
}