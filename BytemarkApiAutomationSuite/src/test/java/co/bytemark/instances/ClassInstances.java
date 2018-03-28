package co.bytemark.instances;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.bytemark.componenttests.workflows.resetpin.ResetPinService;
import co.bytemark.componenttests.workflows.resetpin.ResetPinServiceImpl;
import co.bytemark.models.resetpin.ResetPinRequestModel;


public class ClassInstances {
	
	// Ref var for workflow instance
	protected static ResetPinRequestModel resetPinRequestModel;
	
	//Ref var for service classes
	protected static ResetPinServiceImpl resetPinService;
	// Refvar for spring context.xml
	FileSystemXmlApplicationContext ctx;
	
	public void instance() {
		// This is to get the spring context.xml file
		ctx = new FileSystemXmlApplicationContext("./Resources/SpringConfigFiles/applicationContext.xml");
		//Model class instances
		resetPinRequestModel = (ResetPinRequestModel)ctx.getBean("resetPinrequestModel");
		
		//Service class instances
		resetPinService = (ResetPinServiceImpl) ctx.getBean("resetPinServiceImpl");
	}

}
