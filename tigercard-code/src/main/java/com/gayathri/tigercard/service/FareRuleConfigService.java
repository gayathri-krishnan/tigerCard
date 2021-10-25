package com.gayathri.tigercard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gayathri.tigercard.dto.FareRule;
import com.gayathri.tigercard.dto.FareRuleInput;
import com.gayathri.tigercard.exception.TigerCardErrorCode;
import com.gayathri.tigercard.exception.TigerCardException;
import com.gayathri.tigercard.repository.FareRuleRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FareRuleConfigService {
	private final FareRuleRepository fareRuleRepo;

	public Mono<FareRule> save(final FareRuleInput input) {
		FareRule fareLookup = FareRule.parseInput(input);
		// Check for duplicates
		isUnique(fareLookup);
		return fareRuleRepo.save(fareLookup);
	}

	public List<FareRule> saveList(final List<FareRuleInput> inputs) {

		final List<FareRule> rules = new ArrayList<>();
		inputs.forEach(input -> {
			FareRule fareRule = FareRule.parseInput(input);
			isUnique(fareRule);
			fareRuleRepo.save(fareRule).doOnEach(fareRuleSignal->{
				rules.add(fareRuleSignal.get());
			});
		});
		return rules;
	}

	public Flux<FareRule> getAll() {
		return fareRuleRepo.findAll();
	}

	public Mono<Void> delete(final FareRuleInput input) {
		return fareRuleRepo.delete(FareRule.parseInput(input));
	}

	public Flux<FareRuleInput> getAllParsed() {		
		return fareRuleRepo.findAll().map(FareRuleInput::parseRule);
	}
	
	private boolean isUnique(FareRule fareLookup) {
		fareRuleRepo.getExistingFareRulesInRange(fareLookup).count().doOnEach(c -> {
			if (c.get() > 0) {
				throw new TigerCardException(TigerCardErrorCode.DUPLICATE_INPUT,
						"Fare already exists for the data range provided. Please try with different data.");
			}

		});
		return true;
	}
}
