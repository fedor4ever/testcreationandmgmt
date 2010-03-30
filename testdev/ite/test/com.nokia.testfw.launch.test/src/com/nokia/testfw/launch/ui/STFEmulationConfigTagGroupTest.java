package com.nokia.testfw.launch.ui;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.junit.Test;

public class STFEmulationConfigTagGroupTest extends TestCase {

	STFEmulationConfigTagGroup iSTFEmulationConfigTagGroup = new STFEmulationConfigTagGroup();

	@Test
	public void testCreateTabs() {
		iSTFEmulationConfigTagGroup.createTabs(null, "run");
		assertTrue(iSTFEmulationConfigTagGroup.getTabs().length == 3);
		iSTFEmulationConfigTagGroup.createTabs(null, "debug");
		assertTrue(iSTFEmulationConfigTagGroup.getTabs().length == 7);
	}

	@Test
	public void testSetDefaultsILaunchConfigurationWorkingCopy() {
		ILaunchManager lLaunchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		ILaunchConfigurationType type = lLaunchManager
				.getLaunchConfigurationType("com.nokia.testfw.launch.STFEmulationLaunch");
		ILaunchConfigurationWorkingCopy lLaunchConfigurationWorkingCopy = null;
		try {
			lLaunchConfigurationWorkingCopy = type.newInstance(null,
					"STFProcessorTest");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		iSTFEmulationConfigTagGroup.createTabs(null, "run");
		iSTFEmulationConfigTagGroup
				.setDefaults(lLaunchConfigurationWorkingCopy);
		String hostApp = null;
		try {
			hostApp = lLaunchConfigurationWorkingCopy
					.getAttribute(
							"com.freescale.cdt.debug.cw.core.settings.DebuggerCommonData.Host App Path",
							(String) null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		assertNotNull(hostApp);
	}

}
