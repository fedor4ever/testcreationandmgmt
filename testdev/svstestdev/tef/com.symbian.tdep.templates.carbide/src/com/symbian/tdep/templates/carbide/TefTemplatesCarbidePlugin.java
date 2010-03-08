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



package com.symbian.tdep.templates.carbide;

import java.io.IOException;

import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.symbian.tdep.templates.carbide.contenttype.FileTemplateContextType;

/**
 * The plugin entry class of Carbide template engine.
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
public class TefTemplatesCarbidePlugin extends AbstractUIPlugin {

	private static TefTemplatesCarbidePlugin iTefTemplatesCarbidePlugin;

	private static int iAddFilesToProjectOption;

	public static final String PLUGIN_ID = "com.symbian.tdep.templates.carbide";

	/** The template store and context type registry. */
	private TemplateStore fStore;
	private ContributionContextTypeRegistry fRegistry;
	private static final String CUSTOM_TEMPLATES_KEY = "com.symbian.tdep.templates.carbide.customtemplates";

	public TefTemplatesCarbidePlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		iTefTemplatesCarbidePlugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		iTefTemplatesCarbidePlugin = null;
		super.stop(context);
	}

	/**
	 * A factory method to create a singleton instance of this class
	 * 
	 * @return the default UIPlugin
	 */
	public static TefTemplatesCarbidePlugin getDefault() {
		return iTefTemplatesCarbidePlugin;
	}

	public static int getAddFilesToProjectOption() {
		return iAddFilesToProjectOption;
	}

	public static void setAddFilesToProjectOption(int addFilesToProjectOption) {
		iAddFilesToProjectOption = addFilesToProjectOption;
	}

	/**
	 * Returns this plug-in's template store.
	 * 
	 * @return the template store of this plug-in instance
	 */
	public TemplateStore getTemplateStore() {
		if (fStore == null) {
			fStore = new ContributionTemplateStore(getContextTypeRegistry(),
					getPreferenceStore(), CUSTOM_TEMPLATES_KEY);
			try {
				fStore.load();
			} catch (IOException e) {
				// getLog()
				// .log(
				// new Status(
				// IStatus.ERROR,
				// "com.symbian.tdep.templates", IStatus.OK, "", e));
				// //$NON-NLS-1$ //$NON-NLS-2$
				e.printStackTrace();
			}
		}
		return fStore;
	}

	/**
	 * Returns this plug-in's context type registry.
	 * 
	 * @return the context type registry for this plug-in instance
	 */
	public ContextTypeRegistry getContextTypeRegistry() {
		if (fRegistry == null) {
			// create an configure the contexts available in the template editor
			fRegistry = new ContributionContextTypeRegistry();
			fRegistry
					.addContextType(FileTemplateContextType.FILE_TEMPLATE_CONTEXT_TYPE);
		}
		return fRegistry;
	}
}
