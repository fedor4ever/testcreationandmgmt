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
package com.nokia.testfw.codegen.ui;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.nokia.testfw.codegen.ui.templates.CodeGenTemplateContextType;
import com.nokia.testfw.codegen.ui.templates.EclipseTemplateBuilder;
import com.nokia.testfw.codegen.templates.TemplateBuilderFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class CodegenUIPlugin extends AbstractUIPlugin {

	// The shared instance
	public static CodegenUIPlugin plugin;

	public static String PLUGIN_ID = "com.nokia.testfw.codegen.ui";

	// The template store
	private TemplateStore fStore;

	// The context type registry
	private ContributionContextTypeRegistry fRegistry;

	// The template key
	private static final String TESTFW_TEMPLATES_KEY = "com.nokia.testfw.codegen.templates";

	private IResourceChangeListener iTestFolderDeleteListener;

	/**
	 * The constructor
	 */
	public CodegenUIPlugin() {
		System.setProperty(TemplateBuilderFactory.TEMPLATEBUILDERCLASS,
				EclipseTemplateBuilder.class.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		addTestFolderDeleteListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		removeTestFolderDeleteListener();
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static CodegenUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns this plug-in's template store.
	 * 
	 * @return the template store of this plug-in instance
	 */
	public TemplateStore getTemplateStore() {
		if (fStore == null) {
			fStore = new ContributionTemplateStore(getContextTypeRegistry(),
					getPreferenceStore(), TESTFW_TEMPLATES_KEY) {
				protected void loadContributedTemplates() throws IOException {
					super.loadContributedTemplates();
					Map<String, Template> lPathTemplateMap = EclipseTemplateBuilder
							.findAllOriTemplates();
					for (String path : lPathTemplateMap.keySet()) {
						TemplatePersistenceData data = new TemplatePersistenceData(
								lPathTemplateMap.get(path), true, path);
						internalAdd(data);
					}
				}
			};
			try {
				fStore.load();
			} catch (IOException e) {
				IStatus lStatus = new Status(IStatus.ERROR,
						CodegenUIPlugin.class.getName(),
						"Exception was thrown while loading templates.", e);
				CodegenUIPlugin.getDefault().getLog().log(lStatus);
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
					.addContextType(CodeGenTemplateContextType.FILE_TEMPLATE_CONTEXT_TYPE);
		}
		return fRegistry;
	}

	private void addTestFolderDeleteListener() {
		if (iTestFolderDeleteListener == null)
			iTestFolderDeleteListener = new ResourceChangeListener();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				iTestFolderDeleteListener);
	}

	private void removeTestFolderDeleteListener() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(
				iTestFolderDeleteListener);
	}
}
