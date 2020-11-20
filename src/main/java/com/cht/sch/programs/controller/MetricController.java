package com.cht.sch.programs.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cht.sch.programs.service.ProgramListService;

@RestController
@RequestMapping("/v1/metrics")
public class MetricController {

	@Autowired
	ProgramListService svc;
		
	private static final String myMetric = "my_metric ";
	private Random r = new Random();

	static final String PROGRAM_COUNT = "program_count ";

	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	String metrics() {
		
		return PROGRAM_COUNT.concat(String.valueOf(svc.list().size())).concat("\n")
				.concat(myMetric).concat(String.valueOf(r.nextInt(49)+1));
	}

}
