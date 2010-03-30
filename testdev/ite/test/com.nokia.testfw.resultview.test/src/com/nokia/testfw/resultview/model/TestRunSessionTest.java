package com.nokia.testfw.resultview.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.ISourceLocator;
import org.junit.Before;
import org.junit.Test;

import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestResultListener;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;

public class TestRunSessionTest extends TestCase {

	TestRunSession iTestRunSession;

	boolean isStarted = false;
	boolean isFinished = false;
	boolean isAddTestCase = false;
	boolean isTestCaseStateChange = false;

	@Before
	public void setUp() throws Exception {

		iTestRunSession = new TestRunSession(new ILaunch() {

			public void addDebugTarget(IDebugTarget idebugtarget) {
			}

			public void addProcess(IProcess iprocess) {
			}

			public String getAttribute(String s) {
				return null;
			}

			public Object[] getChildren() {
				return null;
			}

			public IDebugTarget getDebugTarget() {
				return null;
			}

			public IDebugTarget[] getDebugTargets() {
				return null;
			}

			public ILaunchConfiguration getLaunchConfiguration() {
				return null;
			}

			public String getLaunchMode() {
				return null;
			}

			public IProcess[] getProcesses() {
				return null;
			}

			public ISourceLocator getSourceLocator() {
				return null;
			}

			public boolean hasChildren() {
				return false;
			}

			public void removeDebugTarget(IDebugTarget idebugtarget) {
			}

			public void removeProcess(IProcess iprocess) {
			}

			public void setAttribute(String s, String s1) {
			}

			public void setSourceLocator(ISourceLocator isourcelocator) {
			}

			public boolean canTerminate() {
				return false;
			}

			public boolean isTerminated() {
				return false;
			}

			public void terminate() throws DebugException {
			}

			public Object getAdapter(Class arg0) {
				return null;
			}
		}, new IDataProcessor() {

			public void close() throws IOException {
			}

			public ILaunchConfiguration createRetestLaunchConfiguration(
					ILaunchConfiguration configuration,
					TestResult[] retestResults) {
				return configuration;
			}

			public InputStream getInputStream(ILaunchConfiguration config) {
				return new ByteArrayInputStream("TestRunSession".getBytes());
			}

			public boolean isDealType(ILaunchConfiguration config) {
				return false;
			}

			public void process(TestRunResult result, String info) {
				isTestCaseStateChange = true;
			}

			public void initTestResult(TestRunResult result,
					ILaunchConfiguration config) {
			}
		});

		iTestRunSession.addResultListener(new TestResultListener() {

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
	}

	@Test
	public void testTestFinished() {
		iTestRunSession.testFinished();
		assertTrue(isFinished);
	}

	@Test
	public void testGetLaunch() {
		assertNotNull(iTestRunSession.getLaunch());
	}

	@Test
	public void testClose() {
		iTestRunSession.close();
		assertTrue(iTestRunSession.isClosed());
	}

	@Test
	public void testUpdate() {
		String info = "TestCaseStateChange";
		iTestRunSession.update(info);
		assertTrue(isTestCaseStateChange);
	}

	@Test
	public void testGetInputStream() {
		assertNotNull(iTestRunSession.getInputStream());
	}

	@Test
	public void testGetRetestLaunchConfiguration() {
		assertNull(iTestRunSession.getRetestLaunchConfiguration(null, null));
	}

	@Test
	public void testGetStartTime() {
		assertNotNull(iTestRunSession.getStartTime());
	}

}
