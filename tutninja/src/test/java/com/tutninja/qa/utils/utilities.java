package com.tutninja.qa.utils;

import java.util.Date;

public class utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME=5;
	
	
	
	public static String GenerateEmailwithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "pavan" + timestamp + "@gmail.com";

	}
}
