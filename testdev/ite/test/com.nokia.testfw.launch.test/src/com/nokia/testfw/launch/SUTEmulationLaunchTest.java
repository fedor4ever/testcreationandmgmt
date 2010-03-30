package com.nokia.testfw.launch;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationPresentationManager;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationsDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTabGroup;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

import com.nokia.testfw.test.utils.ProjectUtils;
import com.nokia.testfw.test.utils.TestUtils;

@SuppressWarnings("restriction")
public class SUTEmulationLaunchTest extends TestCase {

	IProject targetProject;
	ILaunchConfigurationWorkingCopy iLaunchConfigurationWorkingCopy;

	@Before
	public void setUp() throws Exception {
		targetProject = ProjectUtils.getTargetProject("testRacecar");
		if (targetProject == null) {
			String projectPath = (new File("resource/Racecar"))
					.getCanonicalPath();
			String bldInfPath = "tsrc/group/bld.inf";
			targetProject = ProjectUtils.createTargetProject("testRacecar",
					projectPath, bldInfPath);
		}
		ProjectUtils.selectProject(targetProject);
		ILaunchManager lLaunchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		ILaunchConfigurationType type = lLaunchManager
				.getLaunchConfigurationType("com.nokia.testfw.launch.SUTEmulationLaunch");
		iLaunchConfigurationWorkingCopy = type.newInstance(null,
				"SUTEmulationLaunchTest");
	}

	@Test
	public void testSUTEmulationLaunch() {
		Shell shell = Display.getDefault().getShells()[0];
		String groupIdentifier = "org.eclipse.debug.ui.launchGroup.run";

		org.eclipse.debug.internal.ui.launchConfigurations.LaunchGroupExtension ext = DebugUIPlugin
				.getDefault().getLaunchConfigurationManager().getLaunchGroup(
						groupIdentifier);
		TestUtils.delay(1000);
		if (ext != null) {
			try {
				ILaunchConfigurationTabGroup tabGroup = LaunchConfigurationPresentationManager
						.getDefault().getTabGroup(
								iLaunchConfigurationWorkingCopy,
								ILaunchManager.RUN_MODE);
				LaunchConfigurationsDialog dialog = new LaunchConfigurationsDialog(
						shell, ext);
				dialog.setInitialSelection(new StructuredSelection(
						iLaunchConfigurationWorkingCopy.getType()));
				dialog.setInitialStatus(null);
				dialog.setOpenMode(3);
				dialog.setBlockOnOpen(false);
				dialog.open();

				tabGroup.createTabs(dialog, dialog.getMode());
				ILaunchConfigurationTab tabs[] = tabGroup.getTabs();
				for (int i = 0; i < tabs.length; i++)
					tabs[i].setLaunchConfigurationDialog(dialog);

				tabGroup.setDefaults(iLaunchConfigurationWorkingCopy);
				iLaunchConfigurationWorkingCopy.doSave();
				tabGroup.dispose();
				TestUtils.delay(1000);
				try {
					dialog.getTabGroup().performApply(
							iLaunchConfigurationWorkingCopy);
					String dll = iLaunchConfigurationWorkingCopy
							.getAttribute(LaunchConfigurationConstants.DLLNAME,
									(String) null);
					assertNotNull(dll);
					assertEquals(dll, "ut_racecar.dll");
				} catch (CoreException e) {
					e.printStackTrace();
				}
				dialog.close();
				TestUtils.delay(1000);
			} catch (CoreException e) {
				e.printStackTrace();
			}

		}
	}
}
