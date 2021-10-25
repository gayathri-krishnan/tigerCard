package com.gayathri.tigercard.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gayathri.tigercard.dto.FareCapRule;
import com.gayathri.tigercard.dto.FareCapType;
import com.gayathri.tigercard.dto.FareRule;
import com.gayathri.tigercard.dto.Trip;
import com.gayathri.tigercard.repository.FareCapRuleRepository;
import com.gayathri.tigercard.repository.FareRuleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class FareService {
	private final FareRuleRepository fareLookupRepo;
	private final FareCapRuleRepository fareCapRuleRepo;

	public Mono<Integer> getFare(List<Trip> trips) {
		Map<LocalDate, List<Trip>> dateTripsMap = trips.stream()
				.collect(Collectors.groupingBy(Trip::getTripDate, Collectors.toList()));
		// If there are two different dates, then we need to pick the weekly cap, hence
		// we identify the type
		final FareCapType fareCapType = FareCapType.getFareCapType(dateTripsMap.keySet().size());
		final int totalCap = getFareCap(trips).getRateForType(fareCapType);
		
		return Flux.fromIterable(dateTripsMap.values()).flatMap(list -> calculateDailyRate(list))
				.reduce(0, (x1, x2) -> x1 + x2).map(totalRate -> Math.min(totalRate, totalCap));
	}

	private FareCapRule getFareCap(final List<Trip> trips) {
		final List<Trip> clonedTrips = new ArrayList<>(trips);
		clonedTrips.sort(Comparator.comparing(Trip::getAbsoluteDistance).reversed());
		final Trip tripWithMostDistance = clonedTrips.get(0);
		// find fare cap
		final List<FareCapRule> totalFareCapRules = fareCapRuleRepo.getFareCapRules(tripWithMostDistance);
		if (CollectionUtils.isEmpty(totalFareCapRules)) {
			throw new RuntimeException("Fare Cap Rules not found!!");
		}
		return totalFareCapRules.get(0);
	}

	private Mono<Integer> calculateDailyRate(final List<Trip> trips) {
		// get the fare cap details
		final FareCapRule dailyFareCap = getFareCap(trips);
		Mono<Integer> reduce = Flux.fromIterable(trips).flatMap(trip -> getMatchingFareRule(trip).map(FareRule::getRate))
				.reduce(0, (x1, x2) -> x1 + x2);
		Mono<Integer> map = reduce
				.map(dailyRate -> Math.min(dailyRate, dailyFareCap.getRateForType(FareCapType.DAILY)));
		return map;

	}

	private Mono<FareRule> getMatchingFareRule(final Trip trip) {
		return fareLookupRepo.getFareRules(trip.getSecondOfDay(), trip.getTripDate().getDayOfWeek().getValue(),
				trip.getFromZone(), trip.getToZone()).sort(Comparator.comparing(FareRule::getRate)).onErrorResume(e->Mono.error(new Exception(e))).next();
	}

}
