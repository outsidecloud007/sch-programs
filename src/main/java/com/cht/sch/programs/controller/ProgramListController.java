package com.cht.sch.programs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cht.sch.programs.model.Program;
import com.cht.sch.programs.service.ProgramListService;

@RestController
@RequestMapping("/v1/programs")
public class ProgramListController {

	@Autowired
	ProgramListService svc;
	
	@GetMapping("/version")
    String getVersion() {
        
        return "v1";
    }
	
	@GetMapping
	List<Program> listPrograms() {
		
		return svc.list();
	}

	@PostMapping
	Program editProgram(@Validated @RequestBody Program program) {
		
		return svc.edit(program);
	}
	
	@DeleteMapping("/{startHour}")
	void deleteProgram(@PathVariable int startHour) {
		
		svc.delete(startHour);
	}
	
}
