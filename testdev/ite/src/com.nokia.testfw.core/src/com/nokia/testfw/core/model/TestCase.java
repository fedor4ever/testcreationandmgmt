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
* Contributors: Johnson Ma
*
* Description:
*
*/

package com.nokia.testfw.core.model;

import java.util.Hashtable;
import com.nokia.testfw.core.model.TestResult.TestStatus;

/**
 * A test case in software engineering is a set of conditions or variables under which
 * a tester will determine whether an application or software system is working correctly or not.
 *  
 * <p>
 * The test case class is defined following W3C Test Metadata specification 
 * {@link http://www.w3.org/TR/test-metadata/}
 * <p>
 * the follow attributes defined in the W3C spec are not supported
 * <li>preconditions</li>
 * <li>inputs</li>
 * <li>expectedResults</li>
 */
public class TestCase extends Test {

	/**
	 * A brief explanation of the reason the test was developed
	 */
    protected String purpose;
    
    /**
    * One of an enumerated list of values that can be used to trace the state of a test 
    * at a given time
    */
    protected TestResult result = null;
    
    /**
     * Identification of the portion of the specification tested by this test.
     */
    protected String specRef;
    
    
    /**
     * A mechanism for classifying tests into groups
     */
    protected String[] grouping = null;
    
    /**
     * the properties attribute is defined to allow user to extend test case attributes 
     */
	protected Hashtable<String, Object> properties = new Hashtable<String, Object>();
	
	protected TestSuite suite = null;
	
	public TestCase(String name) {
		this.name = name;
	}
	
	/**
	 * @return the suite
	 */
	public TestSuite getSuite() {
		return suite;
	}


	/**
	 * @param suite the suite to set
	 */
	public void setSuite(TestSuite suite) {
		this.suite = suite;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCase other = (TestCase) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}


	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	/**
	 * @return the result
	 */
	public TestResult getResult() {
		return result;
	}


	/**
	 * @param result the result to set
	 */
	public void setStatus(TestResult result) {
		this.result = result;
	}


	/**
	 * @return the specRef
	 */
	public String getSpecRef() {
		return specRef;
	}


	/**
	 * @param specRef the specRef to set
	 */
	public void setSpecRef(String specRef) {
		this.specRef = specRef;
	}

	/**
	 * @return the grouping
	 */
	public String[] getGrouping() {
		return grouping;
	}


	/**
	 * @param grouping the grouping to set
	 */
	public void setGrouping(String[] grouping) {
		this.grouping = grouping;
	}

	public void addProperty(String key, Object value) {
		properties.put(key, value);
		return;
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	public Hashtable<String, Object> getProperties() {
		return properties;
	}
	

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[testcase]");
		if (name != null) {
		  sb.append(" name:" + name);
		}
//		if (title != null) {
//			  sb.append(" title:" + title);
//		}
		if (purpose != null) {
			  sb.append(" purpoes:" + purpose);
			}
		if (description != null) {
			  sb.append(" description:" + description);
			}
		if (result != null) {
			  sb.append(" result:" + result);
			}
		if (specRef != null) {
			  sb.append(" specRef:" + specRef);
			}
		if (version != null) {
			  sb.append(" version:" + version);
			}
		
				
		return sb.toString();
		
	}

	/**
	 * 
	 */
    public void start() {
    	if (result == null) {
    		result = new TestResult();
    	}
    	result.setStartTime(System.currentTimeMillis());
        result.status = TestResult.TestStatus.STARTED;
    }
    
    /**
     * 
     * @param status, the test case status
     * @parm errorMsg, the error message if any
     */
    public void stop(TestStatus status, String errorMsg) {
        result.setEndTime(System.currentTimeMillis());
        result.status = status;
        suite.updateResult(status);
        if (status == TestStatus.FAILURE) {
        	result.failure = new TestFailure(errorMsg);
        }
        
    }
}
