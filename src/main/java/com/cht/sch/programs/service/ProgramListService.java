package com.cht.sch.programs.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cht.sch.programs.model.Program;
import com.cht.sch.programs.repository.ProgramListRepository;

@Service
public class ProgramListService {

	@Autowired
	ProgramListRepository repo;
	
	public List<Program> list() {
		
		return repo.list();
	}
	
	/**
	 * 新增節目, 限制名稱不能重疊
	 * @param program
	 * @return
	 */
	public Program edit(Program program) {
		
		if (program.getChannel() < 1 || program.getChannel() > 200)
			throw new RuntimeException("The channel must betweenei 1 ~ 200");
			
		List<Program> programs = repo.list();
		
		for (Program p : programs)
			if (p.getName().equals(program.getName()))
				throw new RuntimeException("The program with the same name has existed!");
		
		Program oldProgram = null;
		if (programs.size() > 0)
			for (int i = 0; i < programs.size(); i++) {
				if (programs.get(i).getChannel() == program.getChannel()) {
					oldProgram = programs.set(i, program);
					break;
				}
			}
		
		if (oldProgram == null)
			programs.add(program);
		programs.sort(Comparator.comparing(Program::getChannel));
		repo.update(programs);
		return oldProgram;
	}
	
	public List<Program> delete(int channel) {
		
		List<Program> programs = repo.list();
		programs.removeIf(p -> p.getChannel() == channel);
		repo.update(programs);
		return programs;
	}
}
