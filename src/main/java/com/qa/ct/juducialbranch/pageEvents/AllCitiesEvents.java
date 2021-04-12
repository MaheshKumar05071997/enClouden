package com.qa.ct.juducialbranch.pageEvents;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.qa.ct.juducialbranch.pageObjects.DateObjects;
import com.qa.ct.juducialbranch.utils.ElementFetch;
import com.qa.ct.juducialbranch.utils.ExcelUtil;

public class AllCitiesEvents {

	WebDriver driver;

	public AllCitiesEvents(WebDriver driver) {
		this.driver = driver;
	}

	ElementFetch elementFetch;
	ExcelUtil excelUtil;
	String desiredCities;
	Actions actions;

	public FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public Calendar calender;
	public SimpleDateFormat sdf;
	public LocalDate today;
	// public static LocalDate endDate;
	public String todayDate;

	public void selectRequiredCity() throws InterruptedException, IOException, ParseException {
		elementFetch = new ElementFetch();
		fis = new FileInputStream(System.getProperty("user.dir") + "/testData/ctJudicialData.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		XSSFCell cell = null;

		Iterator iterator = sheet.iterator();
		while (iterator.hasNext()) {
			XSSFRow row = (XSSFRow) iterator.next();
			Iterator cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = (XSSFCell) cellIterator.next();
				System.out.println(cell.toString());
				try {
					WebElement city = elementFetch.getWebElement(driver, "LINKTEXT", cell.toString());
					city.sendKeys(Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString());
				} catch (Exception e) {
					System.out.println(e);
					System.out.println(cell + " Does not exist, please enter city correctly");
				}

				// System.out.println(cell.toString());
				switch (cell.getCellType()) {
				case STRING:
					cell.getStringCellValue();
					break;
				case NUMERIC:
					cell.getNumericCellValue();
					break;
				case BOOLEAN:
					cell.getBooleanCellValue();
					break;
				}
			}
		}

	}

	public String fetchedDateFromTab;
	public String newDate;
	String todaysDate;

	public void getDate() throws ParseException, InterruptedException {
		Set<String> allWindows = driver.getWindowHandles();
		for (String subWindow : allWindows) {
			driver.switchTo().window(subWindow);
			List<WebElement> data = elementFetch.getWebElements(driver, "XPATH", DateObjects.date);
			for (WebElement dataElement : data) {
				String junkData = dataElement.getText();
				// System.out.println(junkData);
				String[] news = junkData.split("\n");
				fetchedDateFromTab = news[0].toString();

				SimpleDateFormat sdfo = new SimpleDateFormat("MM/dd/yyyy");
				Date d1 = sdfo.parse(fetchedDateFromTab);

				String fetchedDateFromTabDate = sdfo.format(d1);
				System.out.println("Dates fetched from each tab " + fetchedDateFromTab);
				sdf = new SimpleDateFormat("MM/dd/yyyy");
				calender = Calendar.getInstance();

				try {
					// Setting the date to the given date
					calender.setTime(sdf.parse(fetchedDateFromTab));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				today = LocalDate.now();
				todayDate = today.toString();
				Format format1 = new SimpleDateFormat("MM/dd/yyyy");
				todaysDate = format1.format(new Date());
				// endDate = today.plusDays(7);
				Date d2 = sdfo.parse(todaysDate);
				String todaysDateDate = sdfo.format(d2);
				System.out.println("Today date is: " + todaysDate);

				System.out.println(todaysDate.getClass().getName());
				// Number of Days to add
				calender.add(Calendar.DAY_OF_MONTH, 7);
				// Date after adding the days to the given date
				newDate = sdf.format(calender.getTime());
				Date d3 = sdfo.parse(newDate);
				String newDateDate = sdfo.format(d3);
				// Displaying the new Date after addition of Days
				System.out.println("Date after 7 days Addition: " + newDateDate);

				elementFetch = new ElementFetch();
				List<WebElement> tobeClicked = elementFetch.getWebElements(driver, "XPATH", DateObjects.viewFullNotice);
				for (WebElement clickElement : tobeClicked) {
					Thread.sleep(3000);
					if (todaysDateDate.compareTo(fetchedDateFromTabDate) >= 0) {
						Thread.sleep(3000);
						clickElement.sendKeys(Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString());
					}

				}
			}
		}

	}
}
