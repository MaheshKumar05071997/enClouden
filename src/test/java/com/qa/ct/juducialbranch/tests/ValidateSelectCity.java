package com.qa.ct.juducialbranch.tests;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import com.qa.ct.juducialbranch.pageEvents.AllCitiesEvents;
import com.qa.ct.juducialbranch.resources.BaseTest;

public class ValidateSelectCity extends BaseTest {

	AllCitiesEvents allCitiesEvents;

	@Test
	public void fetchDesiredCity() throws IOException, InterruptedException, ParseException {
		allCitiesEvents = new AllCitiesEvents(getDriver());
		allCitiesEvents.selectRequiredCity();
		allCitiesEvents.getDate();
	}

}
