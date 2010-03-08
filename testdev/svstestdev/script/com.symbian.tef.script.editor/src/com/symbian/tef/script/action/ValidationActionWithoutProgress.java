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



package com.symbian.tef.script.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.eclipse.ui.part.ISetSelectionTarget;

public class ValidationActionWithoutProgress extends ValidateAction {
	
	private Set<String> errorURIs = new HashSet<String>();

	  protected EclipseResourcesUtilWithLineNumber eclipseResourcesUtilWithLineNumber = 
	    EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE ?
	      new EclipseResourcesUtilWithLineNumber(errorURIs) :
	      null;
	      
	  public static class EclipseResourcesUtilWithLineNumber extends EditUIMarkerHelper
	  {
		  private Set<String> errorURIs = new HashSet<String>();
	    public EclipseResourcesUtilWithLineNumber(Set<String> errorURIs) {
	    	this.errorURIs = errorURIs;
		}

		public IRunnableWithProgress getWorkspaceModifyOperation(IRunnableWithProgress runnableWithProgress)
	    {
	      return new WorkspaceModifyDelegatingOperation(runnableWithProgress);
	    }
	    
	    @Override
	    protected String getMarkerID()
	    {
	      return EValidator.MARKER;
	    }
	    
	    public void createMarkers(Resource resource, Diagnostic diagnostic)
	    {
	      try
	      {
	        createMarkers(getFile(resource), diagnostic, null);
	      }
	      catch (CoreException e)
	      {
	        EMFEditUIPlugin.INSTANCE.log(e);
	      }
	    }

	    @Override
	    protected String composeMessage(Diagnostic diagnostic, Diagnostic parentDiagnostic)
	    {
	      String message = diagnostic.getMessage();
	      if (parentDiagnostic != null)
	      {
	        String parentMessage = parentDiagnostic.getMessage();
	        if (parentMessage != null)
	        {
	          message = message != null ? parentMessage + ". " + message : parentMessage;
	        }
	      }
	      return message;
	    }

	    @Override
	    protected void adjustMarker(IMarker marker, Diagnostic diagnostic, Diagnostic parentDiagnostic) throws CoreException
	    {
	      List<?> data = diagnostic.getData();
	      StringBuilder relatedURIs = new StringBuilder();
	      boolean first = true;
	      for (Object object : data)
	      {
	        if (object instanceof EObject)
	        {
	          EObject eObject = (EObject)object;
	          if (first)
	          {
	            first = false;
	            marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(eObject).toString());
	            errorURIs.add(EcoreUtil.getURI(eObject).toString());
	          }
	          else
	          {
	            if (relatedURIs.length() != 0)
	            {
	              relatedURIs.append(' ');
	            }
	            relatedURIs.append(URI.encodeFragment(EcoreUtil.getURI(eObject).toString(), false));
	          }
	        }
	      }

	      if (relatedURIs.length() > 0)
	      {
	        marker.setAttribute(EValidator.RELATED_URIS_ATTRIBUTE, relatedURIs.toString());
	      }

	      super.adjustMarker(marker, diagnostic, parentDiagnostic);      
	    }
	  }
	
	@Override
	public void run() {
		final Diagnostic diagnostic = validate();
		handleDiagnostic(diagnostic);
	}

	protected Diagnostic validate() {
		EObject eObject = (EObject) selectedObjects.iterator().next();
		int count = 0;
		for (Iterator<?> i = eObject.eAllContents(); i.hasNext(); i.next()) {
			++count;
		}

		final AdapterFactory adapterFactory = domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain) domain)
				.getAdapterFactory()
				: null;

		Diagnostician diagnostician = new Diagnostician() {
			@Override
			public String getObjectLabel(EObject eObject) {
				if (adapterFactory != null && !eObject.eIsProxy()) {
					IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory
							.adapt(eObject, IItemLabelProvider.class);
					if (itemLabelProvider != null) {
						return itemLabelProvider.getText(eObject);
					}
				}

				return super.getObjectLabel(eObject);
			}

			@Override
			public boolean validate(EClass eClass, EObject eObject,
					DiagnosticChain diagnostics, Map<Object, Object> context) {
				return super.validate(eClass, eObject, diagnostics, context);
			}
		};

		return diagnostician.validate(eObject);
	}

	protected void handleDiagnostic(Diagnostic diagnostic) {

		if (eclipseResourcesUtilWithLineNumber != null) {
			Resource resource = domain.getResourceSet().getResources().get(0);
			if (resource != null) {
				eclipseResourcesUtilWithLineNumber.deleteMarkers(resource);
				errorURIs.clear();
			}

			if (true) {
				if (!diagnostic.getChildren().isEmpty()) {
					List<?> data = (diagnostic.getChildren().get(0)).getData();
					if (!data.isEmpty() && data.get(0) instanceof EObject) {
						Object part = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.getActivePart();
						if (part instanceof ISetSelectionTarget) {
							((ISetSelectionTarget) part)
									.selectReveal(new StructuredSelection(data
											.get(0)));
						} else if (part instanceof IViewerProvider) {
							Viewer viewer = ((IViewerProvider) part)
									.getViewer();
							if (viewer != null) {
								viewer.setSelection(new StructuredSelection(
										data.get(0)), true);
							}
						}
					}
				}

				if (resource != null) {
					for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
						eclipseResourcesUtilWithLineNumber.createMarkers(resource, childDiagnostic);
					}
				}
			}
			
			try {
				String MARK_ID = EValidator.MARKER;
				IEditorPart edit = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
				IResource iResource = ((IFileEditorInput) edit.getEditorInput()).getFile();
				for(Resource.Diagnostic e : resource.getErrors()){
				      	IMarker marker = iResource.createMarker(MARK_ID);
				        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
						marker.setAttribute(IMarker.LINE_NUMBER, e.getLine());
						marker.setAttribute(IMarker.LOCATION, "line: " + e.getLine() + ", column: " + e.getColumn());
				        marker.setAttribute(IMarker.MESSAGE, e.getMessage());
			            marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(resource.getContents().get(0)).toString());
				}
				
				for(Resource.Diagnostic e : resource.getWarnings()){
				      	IMarker marker = iResource.createMarker(MARK_ID);
				        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
						marker.setAttribute(IMarker.LINE_NUMBER, e.getLine());
						marker.setAttribute(IMarker.LOCATION, "line: " + e.getLine() + ", column: " + e.getColumn());
				        marker.setAttribute(IMarker.MESSAGE, e.getMessage());
				}
			}
			catch (Exception exception) {
			}
		}
	}

	public Set<String> getErrorURIs() {
		return errorURIs;
	}

	public void setErrorURIs(Set<String> errorsURI) {
		this.errorURIs = errorsURI;
	}
}
