package functionalTests.mainPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.CaptureScreenshots;

public class ListenersTest extends CaptureScreenshots implements ITestListener {
    @Override		
    public void onFinish(ITestContext result) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onStart(ITestContext result) {					
        // TODO Auto-generated method stub
    	System.out.println("Test case started");
        		
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub	
    	try {
			testScreenshot();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	System.out.println("Test case has failed");
    }		

    @Override		
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestStart(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestSuccess(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		
}
