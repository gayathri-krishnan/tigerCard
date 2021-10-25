package com.gayathri.tigercard.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gayathri.tigercard.dto.FareCapRule;
import com.gayathri.tigercard.dto.Trip;

@Repository
public interface FareCapRuleRepository extends MongoRepository<FareCapRule, String> {
	@Query("{$or:[{'zone.start':1,'zone.end':2},{'zone.start':2,'zone.end':1}]}")
	List<FareCapRule> getFareCapRules(@Param("trip")  final Trip trip);
	
}