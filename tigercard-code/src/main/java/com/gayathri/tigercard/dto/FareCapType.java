package com.gayathri.tigercard.dto;

public enum FareCapType {
	DAILY,
	WEEKLY;
	
	public static FareCapType getFareCapType(final int countOfDistinctTripTypes) {
		return countOfDistinctTripTypes==1?DAILY:WEEKLY;
	}
}
