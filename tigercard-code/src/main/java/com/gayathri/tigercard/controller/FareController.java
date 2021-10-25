package com.gayathri.tigercard.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gayathri.tigercard.dto.Trip;
import com.gayathri.tigercard.service.FareService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class FareController {
	
	private final FareService service;
	
	@PostMapping("/fare")
	public Mono<Integer> getFare(@Validated @RequestBody final List<Trip> tripList){
		return service.getFare(tripList);
	}
}
