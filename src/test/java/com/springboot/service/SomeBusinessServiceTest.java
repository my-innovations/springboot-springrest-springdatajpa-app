package com.springboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SomeBusinessServiceTest {

	@Test
	public void calculateSum_basic() {
		SomeBusinessService someBusinessService = new SomeBusinessService();
		int actualResult = someBusinessService.calculateSum(new int[] { 1,2,3});
		
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);	
	}

	@Test
	public void calculateSum_empty() {
		SomeBusinessService someBusinessService = new SomeBusinessService();
		int actualResult = someBusinessService.calculateSum(new int[] { });
		
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);	
	}

	@Test
	public void calculateSum_oneValue() {
		SomeBusinessService someBusinessService = new SomeBusinessService();
		int actualResult = someBusinessService.calculateSum(new int[] { 5});
		
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);	
	}
}
