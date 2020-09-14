package com.springboot.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessServiceTestUsingMockito {

	@InjectMocks
	SomeBusinessService someBusinessService;

	@Mock
	SomeDataService someDataServiceMock;

	@Test
	public void calculateSumUsingDataService_basic() {
		when(someDataServiceMock.retrieveAllData()).thenReturn(new int[] { 1, 2, 3 });
		assertEquals(6, someBusinessService.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		when(someDataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(0, someBusinessService.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		when(someDataServiceMock.retrieveAllData()).thenReturn(new int[] { 5 });
		assertEquals(5, someBusinessService.calculateSumUsingDataService());
	}
}
