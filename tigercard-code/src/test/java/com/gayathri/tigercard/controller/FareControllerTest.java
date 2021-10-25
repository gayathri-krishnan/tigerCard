package com.gayathri.tigercard.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.gayathri.tigercard.dto.Trip;

import reactor.core.publisher.Mono;


@SpringBootTest
@ActiveProfiles("unit-test")
class FareControllerTest {
	
	WebClient webClient;
	
	@BeforeEach
	private void setUp() {
		webClient = WebClient.create("http://localhost:8080");
	}
	
	@Test
    public void testGetFare() {   
		List<Trip> trips = new ArrayList<>();
		Trip trip = Trip.builder().date("2019-05-16").fromZone(1).toZone(1).time("10:30").build();
		trips.add(trip);
		ClientResponse response =  webClient.post()
                .uri("http://localhost:8080/fare")
                .bodyValue(trips)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().block();
       System.out.println(response);
    }
}
