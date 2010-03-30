package com.nokia.testfw.resultview.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.junit.Test;

import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.test.framework.ControlTestCase;
import com.nokia.testfw.test.utils.TestUtils;

public class FailureTraceTest extends ControlTestCase {

	FailureTrace iFailureTrace;

	protected Control createTestControl(Composite parent) {
		parent.setLayout(new GridLayout());
		iFailureTrace = new FailureTrace(parent);
		return iFailureTrace.getTable();
	}

	public void testSetGetUpdate() {
		iFailureTrace.setTestResult(null);
	    TestUtils.delay(1000);	    
		assertEquals(iFailureTrace.getTable().getItemCount(), 0);

		TestResult result = new TestCaseResult("testcase");
		iFailureTrace.setTestResult(result);
	    TestUtils.delay(1000);	    
		assertEquals(iFailureTrace.getTable().getItemCount(), 0);

		result.setMessage("erroe message1");
		iFailureTrace.update(result);
		assertEquals(iFailureTrace.getTable().getItemCount(), 2);
		String msg = iFailureTrace.getTable().getItem(1).getText().trim();
	    TestUtils.delay(1000);	    
		assertEquals(msg, "erroe message1");

		result = iFailureTrace.getTestResult();
	    TestUtils.delay(1000);	    
		assertEquals(result.getName(), "testcase");
		assertEquals(result.getMessage(), "erroe message1");
	}

	public void testSetGetUpdate2() {
		iFailureTrace.setTestResult(null);
	    TestUtils.delay(1000);	    
		assertEquals(iFailureTrace.getTable().getItemCount(), 0);

		TestResult result = new TestCaseResult("testcase");
		result.setMessage("erroe message1");
		iFailureTrace.setTestResult(result);
	    TestUtils.delay(1000);	    
		assertEquals(iFailureTrace.getTable().getItemCount(), 2);
		String msg1 = iFailureTrace.getTable().getItem(1).getText().trim();
		assertEquals(msg1, "erroe message1");

		result.setMessage("erroe message2");
		iFailureTrace.update(result);
	    TestUtils.delay(1000);	    
		String msg2 = iFailureTrace.getTable().getItem(1).getText().trim();
		assertEquals(msg2, "erroe message2");
	}
}
