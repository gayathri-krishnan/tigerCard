package com.gayathri.tigercard.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.gayathri.tigercard.dto.FareRule;

import reactor.core.publisher.Flux;

@Repository
public interface FareRuleRepository extends ReactiveSortingRepository<FareRule, String> {
	@Query("{$and:[time.start': {$gte : ?1},'time.end': {$lt : ?1},'day.start': {$gte : ?2},'day.end': {$lt : ?2}, {$or:[{'zone.start': ?3,'zone.end': ?4}," + 
			"{'zone.end': ?3,'zone.start': ?4}," + 
			"{'zone.end': ?4, 'zone.start': { $ne: ?4}} ]}]}")
	Flux<FareRule> getFareRules(final int secondOfDay, final int day, final int fromZone, final int toZone);
	

	@Query("{$and:[{'time.start': {$gte : :#{#newFareLookUp.time.start}},'time.end': {$lte : :#{#newFareLookUp.time.end}}},"
			+ "{'day.start': {$gte : :#{#newFareLookUp.day.start}},'day.end': {$lte : :#{#newFareLookUp.day.end}}}"
			+ "{$or:[{'zone.start': :#{#newFareLookUp.zone.start},'zone.end': :#{#newFareLookUp.zone.end}},"
			+ "{'zone.end': :#{#newFareLookUp.zone.start},'zone.start': :#{#newFareLookUp.zone.end}},"
			+ "{'zone.end': :#{#newFareLookUp.zone.end}, 'zone.start': { $ne: #{#newFareLookUp.zone.end} }}]}]}")
	Flux<FareRule> getExistingFareRulesInRange(@Param("newFareLookUp") final FareRule newFareLookUp);
}