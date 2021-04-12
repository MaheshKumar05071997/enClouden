package com.qa.ct.juducialbranch.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFetch {
	public WebElement getWebElement(WebDriver driver, String identifierType, String identifierValue) {
		switch (identifierType) {
		case "ID":
			return driver.findElement(By.id(identifierValue));
		case "XPATH":
			return driver.findElement(By.xpath(identifierValue));
		case "LINKTEXT":
			return driver.findElement(By.linkText(identifierValue));
		case "TAGNAME":
			return driver.findElement(By.tagName(identifierValue));
		case "CLASS":
			return driver.findElement(By.className(identifierValue));
		default:
			return null;
		}
	}

	public List<WebElement> getWebElements(WebDriver driver, String identifierType, String identifierValue) {
		switch (identifierType) {
		case "ID":
			return driver.findElements(By.id(identifierValue));
		case "XPATH":
			return driver.findElements(By.xpath(identifierValue));
		case "LINKTEXT":
			return driver.findElements(By.linkText(identifierValue));
		case "TAGNAME":
			return driver.findElements(By.tagName(identifierValue));
		case "CLASS":
			return driver.findElements(By.className(identifierValue));
		default:
			return null;
		}
	}

}
