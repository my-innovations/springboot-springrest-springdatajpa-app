package com.springboot.service;

interface SomeDataService {
	int[] retrieveAllData();
	//int retrieveSpecificData();
}


class SomeDataServiceStub implements SomeDataService {
	
	@Override
	public int[] retrieveAllData() {
		return new int[] { 1, 2, 3 };
	}
}

class SomeDataServiceEmptyStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { };
	}
}

class SomeDataServiceOneElementStub implements SomeDataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 5 };
	}
}

public class SomeBusinessService {
	
	private SomeDataService someDataService;
	
	public void setSomeDataService(SomeDataService someDataService) {
		this.someDataService = someDataService;
	}

	public int calculateSum(int[] data) {
		
		int sum = 0;
		for(int value:data) {
			sum += value;
		}
		return sum;
		//Functional Style
		//return Arrays.stream(data).reduce(Integer::sum).orElse(0);
	}
	
	public int calculateSumUsingDataService() {
		
		int sum = 0;
		int[] data = someDataService.retrieveAllData();
		for(int value:data) {
			sum += value;
		}
		
		//someDataService.storeSum(sum);
		return sum;
		//Functional Style
		//return Arrays.stream(data).reduce(Integer::sum).orElse(0);
	}

}
