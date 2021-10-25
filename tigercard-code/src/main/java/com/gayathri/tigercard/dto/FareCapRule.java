package com.gayathri.tigercard.dto;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Document(collection = "FareCapRule")
public class FareCapRule {
	private final Zone zone;
	private final Map<FareCapType,Integer> capTypeRateMappings; 
	
	public int getRateForType(final FareCapType type) {
		return capTypeRateMappings.get(type);
	}
}
