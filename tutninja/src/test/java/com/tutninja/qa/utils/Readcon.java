package com.tutninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Readcon {
	
Properties pro;
	
	public Readcon()  {
		
		File src = new File("C:\\Users\\swami koragajja\\eclipse-workspace\\tutninja\\Repository\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public String getURL() {
		String URL= pro.getProperty("URL");
		return URL;
	}

}
