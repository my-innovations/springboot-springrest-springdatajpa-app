package com.springboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SomeBusinessServiceStubTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		
		SomeBusinessService someBusinessService = new SomeBusinessService();
		someBusinessService.setSomeDataService(new SomeDataServiceStub());
		int actualResult = someBusinessService.calculateSumUsingDataService();
		
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
		
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBusinessService someBusinessService = new SomeBusinessService();
		someBusinessService.setSomeDataService(new SomeDataServiceEmptyStub());
		int actualResult = someBusinessService.calculateSumUsingDataService();// new int[] {}
		
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBusinessService someBusinessService = new SomeBusinessService();
		someBusinessService.setSomeDataService(new SomeDataServiceOneElementStub());
		int actualResult = someBusinessService.calculateSumUsingDataService();// new int[] { 5 }
		
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}
}
