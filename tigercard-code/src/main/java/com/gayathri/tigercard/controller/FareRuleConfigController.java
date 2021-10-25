package com.gayathri.tigercard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gayathri.tigercard.dto.FareRule;
import com.gayathri.tigercard.dto.FareRuleInput;
import com.gayathri.tigercard.service.FareRuleConfigService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@RestController("/fareRule")
public class FareRuleConfigController {
	private final FareRuleConfigService service;

	@ApiOperation(value = "Retrieve all Fare Rule configurations in the database")
	@GetMapping(value = "/")
	public Flux<FareRule> getAll() {
		log.debug("GET /");
		return service.getAll();
	}
	
	@ApiOperation(value = "Retrieve all Fare Rule configurations in the database")
	@GetMapping(value = "/parsed")
	public Flux<FareRuleInput> getAllParsed() {
		log.debug("GET /");
		return service.getAllParsed();
	}

	@ApiOperation(value = "Create a new Fare Rule configuration in the database")
	@PostMapping(value = "/save")
	public Mono<FareRule> save(@RequestBody final FareRuleInput input) {
		log.debug("POST /save");
		log.debug("fareLookup: " + input.toString());
		return service.save(input);
	}
	
	@ApiOperation(value = "Create all Fare Rule configurations provided in the input")
	@PostMapping(value = "/saveList")
	public List<FareRule> saveList(@RequestBody final List<FareRuleInput> inputs) {
		log.debug("POST /saveList");
		return service.saveList(inputs);
	}
	
	@ApiOperation(value = "Delete a Fare Lookup configuration in the database")
	@DeleteMapping(value = "/delete")
	public Mono<Void> delete(@RequestBody final FareRuleInput input) {
		log.debug("POST /delete");
		log.debug("fareLookup: " + input.toString());
		return service.delete(input);
	}
}
