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
package com.nokia.testfw.resultview.view;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestResultListener;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.resultview.ResultViewPlugin;
import com.nokia.testfw.resultview.WorkspaceUtils;
import com.nokia.testfw.resultview.model.TestRunSession;

/**
 * This class is test result view. it also provide action menu to stop or re-run
 * test execution.
 * 
 * User need to call init() to initialize the view the a particular test result.
 * During the initialization, the view will register a TestResultLister to
 * continue receive any changes from the test result.
 * 
 * <p>
 * Notice: the view controls use the same objects defined in the got from the
 * TestRunResult.
 * <p>
 * 
 * It support ways to update:
 * <li>batch update</li> after all test cases execution, update the result view
 * by pass whole test result.
 * <p>
 * <li>dynamic update</li>
 * during test execution, use listener to track test progress and update the
 * result view dynamically.
 * 
 * @author xiaoma
 * 
 */
public class TestRunnerViewPart extends ViewPart {

	public static final String NAME = "com.nokia.testfw.resultview.testrunnerview";
	private static final Image ICON_STF = ResultViewPlugin
			.getImage("main16/stf.gif"); //$NON-NLS-1$
	private static final ImageDescriptor ICON_LAUNCH_ENABLE = ResultViewPlugin
			.getImageDescriptor("act16/relaunch_enable.gif"); //$NON-NLS-1$
	private static final ImageDescriptor ICON_LAUNCH_DISABLE = ResultViewPlugin
			.getImageDescriptor("act16/relaunch_disable.gif"); //$NON-NLS-1$

	private static final ImageDescriptor ICON_LAUNCHF_ENABLE = ResultViewPlugin
			.getImageDescriptor("act16/relaunchf_enable.gif"); //$NON-NLS-1$
	private static final ImageDescriptor ICON_LAUNCHF_DISABLE = ResultViewPlugin
			.getImageDescriptor("act16/relaunchf_disable.gif"); //$NON-NLS-1$

	private static final ImageDescriptor ICON_HISTORY_LIST = ResultViewPlugin
			.getImageDescriptor("act16/history_list.gif"); //$NON-NLS-1$
	private static final ImageDescriptor ICON_SESSION_RUNNING = ResultViewPlugin
			.getImageDescriptor("act16/tsuiterun.gif"); //$NON-NLS-1$
	private static final ImageDescriptor ICON_SESSION_OK = ResultViewPlugin
			.getImageDescriptor("act16/tsuiteok.gif"); //$NON-NLS-1$
	private static final ImageDescriptor ICON_SESSION_FAIL = ResultViewPlugin
			.getImageDescriptor("act16/tsuitefail.gif"); //$NON-NLS-1$

	public CounterPanel counterPanel;
	public TestProgressBar progressBar;
	// protected SashForm sashForm;
	public TestResultTree resultTree;
	private SashForm iSashForm;
	public Table resultTable;
	private Vector<TestRunSession> iTrackedTestRunSessions = new Vector<TestRunSession>();
	private TestRunSession iCurrentSelectSession = null;
	private Display display;
	private TestResultListener iResultListener;
	private FailureTrace iFailureTrace;
	private RerunLastAction iRerunLastAction;
	private RerunLastFailedAction iRerunLastFailedAction;
	private CLabel iSessionTitle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		parent.setLayout(gridLayout);
		display = parent.getDisplay();

		configureToolBar();
		// create test session name
		iSessionTitle = new CLabel(parent, SWT.SHADOW_OUT);
		iSessionTitle.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		// create counter panel
		createProgressCountPanel(parent);

		iSashForm = createSashForm(parent);
		iSashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

		iResultListener = new TestResultListener() {

			public void testCaseStateChange(final TestCaseResult result,
					final TestStatus status) {
				display.asyncExec(new Runnable() {
					public void run() {
						if (resultTree.getTree().isDisposed())
							return;
						switch (status) {
						case SUCCESS:
							counterPanel.addPassedTest();
							progressBar.step(true);
							break;
						case FAILURE:
							counterPanel.addFailedTest();
							progressBar.step(false);
							break;
						case SKIP:
							progressBar.step(true);
							break;
						}
						resultTree.update(result);
						iFailureTrace.update(result);
					}
				});
			}

			public void testFinished() {
				display.asyncExec(new Runnable() {
					public void run() {
						if (resultTree.getTree().isDisposed())
							return;
						progressBar.stopTest();
						iRerunLastAction.setEnabled(true);
						if (iCurrentSelectSession.getfailedTestCount() > 0) {
							iRerunLastFailedAction.setEnabled(true);
						}
					}
				});
			}

			public void testStarted() {
				// display.asyncExec(new Runnable() {
				// @Override
				// public void run() {
				// progressBar.reset();
				// counterPanel.reset();
				// resultTree.reset();
				// }
				// });
			}

			public void addTestCase(final TestCaseResult result) {
				display.asyncExec(new Runnable() {
					public void run() {
						if (resultTree.addTestCase(result)) {
							progressBar.addTestCount();
							counterPanel.addCounter();
						}
					}
				});
			}

		};
	}

	protected Composite createProgressCountPanel(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		counterPanel = new CounterPanel(composite);
		counterPanel.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		progressBar = new TestProgressBar(composite);
		progressBar.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		composite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		return composite;
	}

	protected void createResultTree(Composite parent) {
		resultTree = new TestResultTree(parent);
	}

	private SashForm createSashForm(Composite parent) {
		SashForm lSashForm = new SashForm(parent, SWT.HORIZONTAL);

		ViewForm top = new ViewForm(lSashForm, SWT.NONE);

		Composite empty = new Composite(top, SWT.NONE);
		empty.setLayout(new Layout() {
			protected Point computeSize(Composite composite, int wHint,
					int hHint, boolean flushCache) {
				return new Point(1, 1); // (0, 0) does not work with
				// super-intelligent ViewForm
			}

			protected void layout(Composite composite, boolean flushCache) {
			}
		});
		top.setTopLeft(empty); // makes ViewForm draw the horizontal separator
		// line ...
		resultTree = new TestResultTree(top);
		final Tree tree = resultTree.getTree();
		tree.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				Item[] sels = tree.getSelection();
				if (sels != null && sels.length > 0) {
					openTestFile((TestResult) sels[0].getData());
				}
			}
		});
		tree.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent keyevent) {
				if (keyevent.keyCode == SWT.CR
						|| keyevent.keyCode == SWT.KEYPAD_CR) {
					Item[] sels = tree.getSelection();
					if (sels != null && sels.length > 0) {
						openTestFile((TestResult) sels[0].getData());
					}
				}
			}
		});
		Menu menu = new Menu(tree);
		final MenuItem item = new MenuItem(menu, SWT.None);
		item.setText("Retest");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem[] items = tree.getSelection();
				if (items != null && items.length > 0) {
					HashSet<TestResult> results = new HashSet<TestResult>();
					for (TreeItem item : items) {
						results.add((TestResult) item.getData());
					}
					rerunTestRun((TestResult[]) results
							.toArray(new TestResult[0]));
				}
			}
		});
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent menuevent) {
				TreeItem[] items = tree.getSelection();
				if (items == null || items.length == 0) {
					item.setEnabled(false);
				} else {
					item.setEnabled(true);
				}
			}
		});
		tree.setMenu(menu);
		top.setContent(tree);

		ViewForm bottom = new ViewForm(lSashForm, SWT.NONE);

		CLabel label = new CLabel(bottom, SWT.NONE);
		label.setText("Failure information:");
		label.setImage(ICON_STF);
		bottom.setTopLeft(label);
		ToolBar failureToolBar = new ToolBar(bottom, SWT.FLAT | SWT.WRAP);
		bottom.setTopCenter(failureToolBar);
		iFailureTrace = new FailureTrace(bottom);
		resultTable = iFailureTrace.getTable();
		resultTable.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				openTestFile(iFailureTrace.getTestResult());
			}
		});
		resultTable.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent keyevent) {
				if (keyevent.keyCode == SWT.CR
						|| keyevent.keyCode == SWT.KEYPAD_CR) {
					openTestFile(iFailureTrace.getTestResult());
				}
			}
		});
		bottom.setContent(resultTable);

		tree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				iFailureTrace.setTestResult((TestResult) event.item.getData());
			}
		});

		lSashForm.setWeights(new int[] { 50, 50 });
		return lSashForm;
	}

	private void configureToolBar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(iRerunLastAction = new RerunLastAction());
		toolBar.add(iRerunLastFailedAction = new RerunLastFailedAction());
		toolBar.add(new Separator());
		toolBar.add(new HistoryDropDownAction());

		actionBars.updateActionBars();
	}

	private void openTestFile(TestResult result) {
		if (result == null) {
			ResultViewPlugin.log(IStatus.WARNING,
					"can't find selected test result");
			return;
		}
		// find the file
		if (result.getFile() == null) {
			ResultViewPlugin.log(IStatus.OK,
					"no locaiton information associated with the test");
			return;
		}
		// find file in workspace
		WorkspaceUtils.openFile(result.getFile(), result.getLine(), result
				.getColumn());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		iSashForm.setFocus();
	}

	public void clear() {
		reset();
		iTrackedTestRunSessions.clear();
	}

	private void reset() {
		if (iCurrentSelectSession != null) {
			iCurrentSelectSession.removeResultListener(iResultListener);
		}
		progressBar.reset();
		counterPanel.reset();
		resultTree.reset();
		resultTable.removeAll();
		iCurrentSelectSession = null;
	}

	public void addTestRunSession(TestRunSession session) {
		iTrackedTestRunSessions.add(session);
		show(session);
	}

	/**
	 * Initialize the result view with tests Please notice, those tests may not
	 * been executed yet. create result listener to get update events
	 * 
	 * @param result
	 * @see TestRunResult
	 */
	private void show(TestRunSession session) {
		reset();
		// init progress bar
		progressBar.init(session);
		// init counter
		counterPanel.init(session);
		// init tree
		resultTree.init(session);
		// during initialize, we will create test result lister to monitor
		// any updates on the test run result
		session.addResultListener(iResultListener);
		iSessionTitle.setText(session.getName());
		iCurrentSelectSession = session;

		if (session.isClosed()) {
			iRerunLastAction.setEnabled(true);
			if (session.getfailedTestCount() > 0) {
				iRerunLastFailedAction.setEnabled(true);
			}
		} else {
			iRerunLastAction.setEnabled(false);
			iRerunLastFailedAction.setEnabled(false);
		}
	}

	public boolean isCreated() {
		return counterPanel != null;
	}

	private class ClearTerminatedAction extends Action {
		public ClearTerminatedAction() {
			setText("&Clear Terminated");

			boolean enabled = false;
			for (Iterator<TestRunSession> iter = iTrackedTestRunSessions
					.iterator(); iter.hasNext();) {
				TestRunSession testRunSession = iter.next();
				if (testRunSession.isClosed()) {
					enabled = true;
					break;
				}
			}
			setEnabled(enabled);
		}

		public void run() {
			for (Iterator<TestRunSession> iter = iTrackedTestRunSessions
					.iterator(); iter.hasNext();) {
				TestRunSession testRunSession = iter.next();
				if (testRunSession.isClosed()) {
					iter.remove();
				}
			}
			if (!iTrackedTestRunSessions.isEmpty()) {
				show(iTrackedTestRunSessions.lastElement());
			} else {
				reset();
			}
		}
	}

	private class RerunLastAction extends Action {
		public RerunLastAction() {
			setText("Rerun Test");
			setToolTipText("Rerun Test");
			setDisabledImageDescriptor(ICON_LAUNCH_DISABLE);
			setHoverImageDescriptor(ICON_LAUNCH_ENABLE);
			setImageDescriptor(ICON_LAUNCH_ENABLE);
			setEnabled(false);
			setActionDefinitionId("com.nokia.testfw.resultview.testrunnerview.reruntest");
		}

		public void run() {
			rerunTestRun();
		}
	}

	private class RerunLastFailedAction extends Action {
		public RerunLastFailedAction() {
			setText("Rerun Test - Failures");
			setToolTipText("Rerun Test - Failures");
			setDisabledImageDescriptor(ICON_LAUNCHF_DISABLE);
			setHoverImageDescriptor(ICON_LAUNCHF_ENABLE);
			setImageDescriptor(ICON_LAUNCHF_ENABLE);
			setEnabled(false);
			setActionDefinitionId("com.nokia.testfw.resultview.testrunnerview.rerunFailedFirst");
		}

		public void run() {
			rerunTestFailed();
		}
	}

	private class HistoryAction extends Action {
		private TestRunSession iSession;

		public HistoryAction(TestRunSession session) {
			super("", AS_RADIO_BUTTON); //$NON-NLS-1$
			iSession = session;

			setText(session.getName());
			if (session.isClosed()) {
				if (session.getfailedTestCount() > 0) {
					setImageDescriptor(ICON_SESSION_FAIL);
				} else {
					setImageDescriptor(ICON_SESSION_OK);
				}
			} else {
				setImageDescriptor(ICON_SESSION_RUNNING);
			}
		}

		public void run() {
			show(iSession);
		}
	}

	private class HistoryDropDownAction extends Action {

		public HistoryDropDownAction() {
			super("Test Run History...", IAction.AS_DROP_DOWN_MENU);
			setImageDescriptor(ICON_HISTORY_LIST);

			setMenuCreator(new IMenuCreator() {
				private Menu iMenu;

				public Menu getMenu(Menu menu) {
					return null;
				}

				public Menu getMenu(Control parent) {
					if (iMenu != null) {
						iMenu.dispose();
					}
					final MenuManager manager = new MenuManager();
					manager.setRemoveAllWhenShown(true);
					manager.addMenuListener(new IMenuListener() {
						public void menuAboutToShow(IMenuManager manager2) {
							for (TestRunSession session : iTrackedTestRunSessions) {
								HistoryAction action = new HistoryAction(
										session);
								action
										.setChecked(session == iCurrentSelectSession);
								manager2.add(action);
							}
							manager2.add(new Separator());
							manager2.add(new ClearTerminatedAction());
						}
					});

					iMenu = manager.createContextMenu(parent);

					// workaround for
					// https://bugs.eclipse.org/bugs/show_bug.cgi?id=129973
					final Display display = parent.getDisplay();
					iMenu.addMenuListener(new MenuAdapter() {
						public void menuHidden(final MenuEvent e) {
							display.asyncExec(new Runnable() {
								public void run() {
									manager.removeAll();
									if (iMenu != null) {
										iMenu.dispose();
										iMenu = null;
									}
								}
							});
						}
					});
					return iMenu;
				}

				public void dispose() {
					if (iMenu != null) {
						iMenu.dispose();
						iMenu = null;
					}
				}
			});
		}

	}

	private void rerunTestRun() {

		if (iCurrentSelectSession == null)
			return;
		ILaunch launch = iCurrentSelectSession.getLaunch();
		if (launch == null)
			return;
		ILaunchConfiguration launchConfiguration = launch
				.getLaunchConfiguration();
		if (launchConfiguration == null)
			return;

		DebugUITools.launch(launchConfiguration, launch.getLaunchMode());
	}

	private void rerunTestRun(TestResult[] results) {
		if (iCurrentSelectSession == null)
			return;

		ILaunch launch = iCurrentSelectSession.getLaunch();
		if (launch != null && launch.getLaunchConfiguration() != null) {
			ILaunchConfiguration launchConfiguration = launch
					.getLaunchConfiguration();
			if (launchConfiguration != null) {
				launchConfiguration = iCurrentSelectSession
						.getRetestLaunchConfiguration(launchConfiguration,
								results);
				DebugUITools
						.launch(launchConfiguration, launch.getLaunchMode());
			}
		}
	}

	private void rerunTestFailed() {
		if (!iCurrentSelectSession.isClosed())
			return;
		rerunTestRun(iCurrentSelectSession.getFailedResults().toArray(
				new TestResult[0]));
	}
}
