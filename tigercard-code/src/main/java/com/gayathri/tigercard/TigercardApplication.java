package com.gayathri.tigercard;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayathri.tigercard.dto.Day;
import com.gayathri.tigercard.dto.FareRule;
import com.gayathri.tigercard.dto.FareRuleInput;
import com.gayathri.tigercard.dto.Time;
import com.gayathri.tigercard.dto.Zone;
@EnableMongoRepositories
@SpringBootApplication
public class TigercardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TigercardApplication.class, args); 
	    final Time travelTime = new Time(LocalTime.of(7, 0), LocalTime.of(10, 30));
		final Zone travelZone = new Zone(1, 1);
		final Day travelDay = new Day(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
		FareRule peakFare1 = FareRule.builder().time(travelTime).zone(travelZone).day(travelDay).rate(30).build();
		FareRuleInput input = FareRuleInput.builder().startTime("07:00").endTime("10:30").zone(travelZone).startDay(DayOfWeek.MONDAY).endDay(DayOfWeek.FRIDAY).rate(30).build();
		try {
			System.out.println(new ObjectMapper().writeValueAsString(input));
			} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
