package com.gayathri.tigercard.dto;

import com.mongodb.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Getter
public class Zone {
	@Nullable
	private int start;	
	private int end;
	
	@Builder
	public Zone(int end) {
		this.end = end;
	}
	
	@Builder
	public Zone(int start, int end) {
		this.start = start;
		this.end = end;
	}	

}
