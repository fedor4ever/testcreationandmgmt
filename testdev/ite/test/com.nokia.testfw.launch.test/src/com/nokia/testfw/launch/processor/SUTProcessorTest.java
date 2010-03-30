package com.nokia.testfw.launch.processor;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.junit.Before;
import org.junit.Test;

import com.nokia.cdt.debug.cw.symbian.SettingsData;
import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestResultListener;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.launch.LaunchConfigurationConstants;
import com.nokia.testfw.test.utils.ProjectUtils;

@SuppressWarnings("restriction")
public class SUTProcessorTest extends TestCase {

	ILaunchConfigurationWorkingCopy iLaunchConfigurationWorkingCopy;
	boolean isStarted = false;
	boolean isFinished = false;
	boolean isAddTestCase = false;
	boolean isTestCaseStateChange = false;
	IProject targetProject;

	@Before
	public void setUp() throws Exception {
		targetProject = ProjectUtils.getTargetProject("testRacecar");
		if (targetProject == null) {
			String projectPath = (new File("resource/Racecar"))
					.getCanonicalPath();
			String bldInfPath = "tsrc/group/Bld.inf";
			targetProject = ProjectUtils.createTargetProject("testRacecar",
					projectPath, bldInfPath);
		}
		ProjectUtils.selectProject(targetProject);

		ILaunchManager lLaunchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		ILaunchConfigurationType type = lLaunchManager
				.getLaunchConfigurationType("com.nokia.testfw.launch.SUTEmulationLaunch");
		iLaunchConfigurationWorkingCopy = type.newInstance(null,
				"SUTProcessorTest");

		SettingsData
				.setDefaults(
						iLaunchConfigurationWorkingCopy,
						"com.nokia.cdt.debug.cw.symbian.SettingsData.LaunchConfig_Emulator",
						targetProject);
		iLaunchConfigurationWorkingCopy.setAttribute(
				"org.eclipse.cdt.launch.PROGRAM_ARGUMENTS", "-d=ut_racecar.dll");
		iLaunchConfigurationWorkingCopy.setAttribute(
				LaunchConfigurationConstants.DLLNAME, "ut_racecar.dll");
	}

	@Test
	public void testProcess() {

		TestRunResult lTestRunResult = new TestRunResult();
		lTestRunResult.addResultListener(new TestResultListener() {

			public void addTestCase(TestCaseResult result) {
				isAddTestCase = true;
			}

			public void testCaseStateChange(TestCaseResult result,
					TestStatus status) {
				isTestCaseStateChange = true;
			}

			public void testFinished() {
				isFinished = true;
			}

			public void testStarted() {
				isStarted = true;
			}
		});
		String info = "SymbianUnitTest v1.0.0\n" + "TestCase[testcase1]\n"
				+ "StartCase[testcase1]\n" + "EndCase Result[fail] Time[5]ms\n"
				+ "SymbianUnitTest finished";
		new SUTProcessor().process(lTestRunResult, info);
		assertTrue(isStarted);
		assertTrue(isFinished);
		assertTrue(isAddTestCase);
		assertTrue(isTestCaseStateChange);
	}

	@Test
	public void testGetInputStream() {
		assertNotNull(new SUTProcessor()
				.getInputStream(iLaunchConfigurationWorkingCopy));
	}

	@Test
	public void testIsDealType() {
		assertTrue(new SUTProcessor()
				.isDealType(iLaunchConfigurationWorkingCopy));
	}

	@Test
	public void testCreateRetestLaunchConfiguration() {
		TestResult[] retestResults = new TestResult[] { new TestCaseResult(
				"testcase") };
		ILaunchConfiguration newconfig = new SUTProcessor()
				.createRetestLaunchConfiguration(
						iLaunchConfigurationWorkingCopy, retestResults);
		String args = null;
		try {
			args = newconfig.getAttribute(
					"org.eclipse.cdt.launch.PROGRAM_ARGUMENTS", (String) null);
		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		assertNotNull(args);
		assertTrue(args.indexOf("-c=testcase") > -1);
	}
}
