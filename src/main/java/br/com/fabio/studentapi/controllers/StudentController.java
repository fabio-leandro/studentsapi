package br.com.fabio.studentapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.fabio.studentapi.dto.request.StudentDTO;
import br.com.fabio.studentapi.dto.response.MessageResponseDTO;
import br.com.fabio.studentapi.exceptions.StudentNotFoundException;
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
	
	@GetMapping
	public List<StudentDTO> listAll(){
		return studentService.listAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentDTO findById(@PathVariable Long id) throws StudentNotFoundException{
		return studentService.findById(id);
	}

}
