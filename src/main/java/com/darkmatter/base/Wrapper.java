package com.darkmatter.base;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Wrapper {

	private static Wrapper baseobj;
	public static HashMap<String, String> todoListMap = new HashMap<String, String>();

	/*
	 * Create a static method to get instance.
	 */
	public static Wrapper getInstance() {
		if (baseobj == null) {
			baseobj = new Wrapper();
		}
		return baseobj;
	}

	static public WebDriver driver = null;
	WebElement element;
	public String xmlelementvalue = null;

	public void settDriver(WebDriver driver) {
		Wrapper.driver = driver;
	}

	public WebDriver getDriver() {
		return Wrapper.driver;
	}

	public HashMap<String, String> readXML(String fileName, String tag) {
		
		fileName = System.getProperty("user.dir")+"/src/test/resources/resource/"+fileName+".xml";
		File fXmlFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName(tag);

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			NodeList childNodes = nNode.getChildNodes();

			for (int index = 0; index < childNodes.getLength(); index++) {
				Node tempNode = childNodes.item(index);
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					todoListMap.put(tempNode.getNodeName(), tempNode.getTextContent());
					
				}
			}
		}
		return todoListMap;
	}


		public void closeCurrentWindow() {
		driver.close();
	}

	public void closeAllWindow() {
		driver.quit();
	}

	public void closeChromeInstance() {
		try {
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

}