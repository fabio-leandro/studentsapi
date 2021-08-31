package br.com.fabio.studentapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public StudentNotFoundException(Long id) {
		
		super(String.format("Student with ID %s not found!", id));
	}
	

}
