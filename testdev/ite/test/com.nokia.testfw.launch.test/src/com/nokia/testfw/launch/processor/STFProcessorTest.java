package com.nokia.testfw.launch.processor;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.junit.Before;
import org.junit.Test;

import com.nokia.cdt.debug.cw.symbian.SettingsData;
import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResultListener;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.launch.LaunchConfigurationConstants;
import com.nokia.testfw.test.utils.ProjectUtils;

@SuppressWarnings("restriction")
public class STFProcessorTest extends TestCase {

	ILaunchConfigurationWorkingCopy iLaunchConfigurationWorkingCopy;
	boolean isStarted = false;
	boolean isFinished = false;
	boolean isAddTestCase = false;
	boolean isTestCaseStateChange = false;
	IProject targetProject;

	@Before
	public void setUp() throws Exception {
		targetProject = ProjectUtils.getTargetProject("TestModule");
		if (targetProject == null) {
			String projectPath = (new File("resource/TestModule"))
					.getCanonicalPath();
			String bldInfPath = "group/bld.inf";
			targetProject = ProjectUtils.createTargetProject("TestModule",
					projectPath, bldInfPath);
		}
		ProjectUtils.selectProject(targetProject);

		ILaunchManager lLaunchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		ILaunchConfigurationType type = lLaunchManager
				.getLaunchConfigurationType("com.nokia.testfw.launch.STFEmulationLaunch");
		iLaunchConfigurationWorkingCopy = type.newInstance(null,
				"STFProcessorTest");

		SettingsData
				.setDefaults(
						iLaunchConfigurationWorkingCopy,
						"com.nokia.cdt.debug.cw.symbian.SettingsData.LaunchConfig_Emulator",
						targetProject);
		iLaunchConfigurationWorkingCopy.setAttribute(
				"org.eclipse.cdt.launch.PROGRAM_ARGUMENTS",
				"-m TestScripter -s C:\\TestFramework\\TestModuleTests.cfg");
		String host_path = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().append("TestModule\\group\\TestModuleTests.cfg")
				.toString();
		iLaunchConfigurationWorkingCopy.setAttribute(
				LaunchConfigurationConstants.SCRIPT_HOST_PATH, host_path);
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
		String info = "19.Jan.2010 09:01:29.596....############################################################\n"
				+ "19.Jan.2010 09:01:29.606....CTestEngine::AddTestModuleL, Adding Module:[testscripter]\n"
				+ "19.Jan.2010 09:01:29.606....NOTE: Test module is TestScripter and each test case(config) file(s) will have own server(s)\n"
				+ "19.Jan.2010 09:01:29.606....CTestEngine::AddTestModuleL, module added correctly\n"
				+ "19.Jan.2010 09:01:29.606....Adding config file [c:\testframework\testmoduletests.cfg] to [testscripter] module\n"
				+ "19.Jan.2010 09:01:29.606....CTestScripterController::AddConfigFileL aConfigFile=[c:\testframework\testmoduletests.cfg]\n"
				+ "19.Jan.2010 09:01:29.606....Creating CTestModuleController [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:29.611....Initialising test module [testscripter_testmoduletests] with initialization file []\n"
				+ "19.Jan.2010 09:01:29.656....Initialising test module [testscripter_testmoduletests] with initialization file [] done\n"
				+ "19.Jan.2010 09:01:29.656....CTestModuleController::AddConfigFileL [testscripter_testmoduletests] aConfigFile=[c:\testframework\testmoduletests.cfg]\n"
				+ "19.Jan.2010 09:01:29.718....CTestEngine::EnumerateTestCasesL\n"
				+ "19.Jan.2010 09:01:29.718....Getting testcases from module [testscripter_testmoduletests], test case file[c:\testframework\testmoduletests.cfg]\n"
				+ "19.Jan.2010 09:01:29.801....RunL()'s GetTestCases method returns: 0\n"
				+ "19.Jan.2010 09:01:29.801....Test case enumeration completed, testcase count 11\n"
				+ "19.Jan.2010 09:01:29.801....Module controllers handling mode: normal\n"
				+ "19.Jan.2010 09:01:29.801....Find test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:29.801....Found test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:29.806.... Starting testcase [Passing case]\n"
				+ "19.Jan.2010 09:01:30.140.... TestCase [Passing case] finished with verdict[-2]\n"
				+ "19.Jan.2010 09:01:31.958....Module controllers handling mode: normal\n"
				+ "19.Jan.2010 09:01:31.958....Find test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:31.958....Found test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:31.958.... Starting testcase [Printing with too long text]\n"
				+ "19.Jan.2010 09:01:32.296.... TestCase [Printing with too long text] finished with verdict[-2]\n"
				+ "19.Jan.2010 09:01:32.301....Module controllers handling mode: normal\n"
				+ "19.Jan.2010 09:01:32.301....Find test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:32.301....Found test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:32.301.... Starting testcase [Printing with too long desc&text]\n"
				+ "19.Jan.2010 09:01:32.625.... TestCase [Printing with too long desc&text] finished with verdict[-2]\n"
				+ "19.Jan.2010 09:01:33.301....Module controllers handling mode: normal\n"
				+ "19.Jan.2010 09:01:33.301....Find test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:33.301....Found test module controller for [testscripter_testmoduletests]\n"
				+ "19.Jan.2010 09:01:33.301.... Starting testcase [Printing with empty desc twice]\n"
				+ "19.Jan.2010 09:01:33.640.... TestCase [Printing with empty desc twice] finished with verdict[-2]\n"
				+ "19.Jan.2010 09:01:33.645....CTestEngine::RemoveTestModuleL\n"
				+ "19.Jan.2010 09:01:33.645....Going to remove module controller [testscripter]\n"
				+ "19.Jan.2010 09:01:33.645....Removing module controller [testscripter]\n"
				+ "19.Jan.2010 09:01:33.650....CTestEngine::CloseTestEngineL\n"
				+ "19.Jan.2010 09:01:33.660....CTestEngine::CloseSession\n"
				+ "19.Jan.2010 09:01:33.660....---------------- Log Ended ----------------\n";
		STFProcessor processor = new STFProcessor();
		processor.initTestResult(lTestRunResult,
				iLaunchConfigurationWorkingCopy);
		processor.process(lTestRunResult, info);
		assertTrue(isStarted);
		assertTrue(isFinished);
		assertTrue(isTestCaseStateChange);
	}

	@Test
	public void testGetInputStream() {
		assertNotNull(new STFProcessor()
				.getInputStream(iLaunchConfigurationWorkingCopy));
	}

	@Test
	public void testIsDealType() {
		assertTrue(new STFProcessor()
				.isDealType(iLaunchConfigurationWorkingCopy));
	}

	@Test
	public void testCreateRetestLaunchConfiguration() {
		return;
	}

	@Test
	public void testInitTestResult() {
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
		new STFProcessor().initTestResult(lTestRunResult,
				iLaunchConfigurationWorkingCopy);
		assertTrue(isAddTestCase);
	}
}
