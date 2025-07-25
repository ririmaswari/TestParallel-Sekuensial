package com.juaracoding.ecom;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListernerTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("on test start: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }


}
