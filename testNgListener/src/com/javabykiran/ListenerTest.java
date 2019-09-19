package com.javabykiran;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest  implements ITestListener {
	
	@Override
	public void onFinish(ITestContext result) {
			System.out.println("test cases finished Name >>  :: "+result.getName() );
			System.out.println("finish  time >> "+new java.util.Date());
	}

	@Override
	public void onStart(ITestContext result) {
		System.out.println("test cases finished time >>  :: "+result.getName() );
		System.out.println("start time >> "+new java.util.Date());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	
	// This method takes screen shots after test case failed.
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			CaptureScreenShot.capture(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
