package br.com.fabio.studentapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fabio.studentapi.builder.StudentDTOBuilder;
import br.com.fabio.studentapi.dtos.mapper.StudentMapper;
import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.dtos.response.MessageResponseDTO;
import br.com.fabio.studentapi.entities.Student;
import br.com.fabio.studentapi.repositories.StudentRepository;
import br.com.fabio.studentapi.services.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private StudentMapper studentMapper;
	
	
	@InjectMocks
	private StudentService studentService;
	
	@Test
    void whenStudentInformedThenItShouldBeCreatedAndReturnSucessWithId() {
		// given
	    StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
	    Student expectedSavedStudent = studentMapper.toModel(expectedStudentDTO);

	    // when
	    when(studentRepository.save(expectedSavedStudent)).thenReturn(expectedSavedStudent);

	    //then
	    MessageResponseDTO messageStudentExpected = 
	    		studentService.createMessageResponseDTO("Sucess", expectedStudentDTO.getId());
	     
	    MessageResponseDTO messageStudentSaved =
	    		studentService.createMessageResponseDTO("Sucess ", expectedSavedStudent.getId());
	    
	    assertThat(messageStudentSaved, is(equalTo(messageStudentExpected)));
	    
		
	}
	
	


}
