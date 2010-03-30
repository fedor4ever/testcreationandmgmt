package com.nokia.testfw.launch.ui;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.junit.Test;

public class SUTEmulationConfigTagGroupTest extends TestCase {

	SUTEmulationConfigTagGroup iSUTEmulationConfigTagGroup = new SUTEmulationConfigTagGroup();

	@Test
	public void testCreateTabs() {
		iSUTEmulationConfigTagGroup.createTabs(null, "run");
		assertTrue(iSUTEmulationConfigTagGroup.getTabs().length == 3);
		iSUTEmulationConfigTagGroup.createTabs(null, "debug");
		assertTrue(iSUTEmulationConfigTagGroup.getTabs().length == 7);
	}

	@Test
	public void testSetDefaultsILaunchConfigurationWorkingCopy() {
		ILaunchManager lLaunchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		ILaunchConfigurationType type = lLaunchManager
				.getLaunchConfigurationType("com.nokia.testfw.launch.SUTEmulationLaunch");
		ILaunchConfigurationWorkingCopy lLaunchConfigurationWorkingCopy = null;
		try {
			lLaunchConfigurationWorkingCopy = type.newInstance(null,
					"SUTProcessorTest");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iSUTEmulationConfigTagGroup.createTabs(null, "run");
		iSUTEmulationConfigTagGroup
				.setDefaults(lLaunchConfigurationWorkingCopy);
		String hostApp = null;
		try {
			hostApp = lLaunchConfigurationWorkingCopy
					.getAttribute(
							"com.freescale.cdt.debug.cw.core.settings.DebuggerCommonData.Host App Path",
							(String) null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(hostApp);
	}

}
