package br.com.fabio.studentapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabio.studentapi.dto.request.StudentDTO;
import br.com.fabio.studentapi.dto.response.MessageResponseDTO;
import br.com.fabio.studentapi.services.StudentService;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO create(@RequestBody @Valid StudentDTO studentDTO) {
		return studentService.create(studentDTO);
		
	}

}
