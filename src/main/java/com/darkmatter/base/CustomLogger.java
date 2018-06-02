package com.darkmatter.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CustomLogger {

	public static Logger Log;

	
	public static void start(String messgae) {
		PropertyConfigurator.configure("Log4j.properties");
		Log = Logger.getLogger(new Exception().getStackTrace()[1].getClassName());
		Log.info(messgae);
		//Log.info(">>>>>>>>>>>>>>>>>>>>>" + TestCaseName+ "<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName) {
		Log.info("-----------------------" + "-E---N---D-"+ "-----------------------");
	}
}
