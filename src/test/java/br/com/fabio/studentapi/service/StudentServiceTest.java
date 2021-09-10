package br.com.fabio.studentapi.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
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
import br.com.fabio.studentapi.exceptions.StudentNotFoundException;
import br.com.fabio.studentapi.repositories.StudentRepository;
import br.com.fabio.studentapi.services.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;

	private StudentMapper studentMapper = StudentMapper.INSTANCE;

	@InjectMocks
	private StudentService studentService;

	@Test
	void whenStudentInformedThenItShouldBeCreated() {

		// given
		StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		Student expectedStudent = studentMapper.toModel(expectedStudentDTO);
		MessageResponseDTO expectedMessageResponseDTO = studentService
				.createMessageResponseDTO("Student successfully created with ID ", expectedStudent.getId());

		// When
		when(studentRepository.save(expectedStudent)).thenReturn(expectedStudent);

		// then
		MessageResponseDTO messageResponseCreated = studentService.create(expectedStudentDTO);

		MatcherAssert.assertThat(expectedMessageResponseDTO.getMessage(),
				is(equalTo(messageResponseCreated.getMessage())));

	}

	@Test
	void whenCalledItShouldReturnedOneListOfStudents() {

		// given
		StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		Student expectedStudent = studentMapper.toModel(expectedStudentDTO);

		List<Student> expectedStudents = new ArrayList<>();
		expectedStudents.add(expectedStudent);

		// When
		when(studentRepository.findAll()).thenReturn(expectedStudents);

		// then
		List<StudentDTO> expectedStudentsDtos = studentService.listAll();
		expectedStudentsDtos.add(expectedStudentDTO);

		MatcherAssert.assertThat(expectedStudents.get(0).getId(), is(equalTo(expectedStudentsDtos.get(0).getId())));

	}

	@Test
	void whenStudentIdInformedItShouldThrowsStudentNotFoundException() {

		// given
		StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		Student expectedStudent = studentMapper.toModel(expectedStudentDTO);
		// when
		when(studentRepository.findById(expectedStudent.getId())).thenReturn(Optional.empty());

		// then
		assertThrows(StudentNotFoundException.class, () -> studentService.findById(expectedStudent.getId()));

	}

	@Test
	void whenStudentIdInformedItShouldReturnedStudentWithThisId() throws StudentNotFoundException {

		// given
		StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		Student expectedStudent = studentMapper.toModel(expectedStudentDTO);
		
		// when
		when(studentRepository.findById(expectedStudent.getId()))
		.thenReturn(Optional.of(expectedStudent));
		
		// then
		
		StudentDTO studentFoundDTO = studentService.findById(expectedStudent.getId());
		
		
		MatcherAssert.assertThat(studentFoundDTO, is(equalTo(expectedStudentDTO)));
		
		
		

	}

	@Test
	void whenStudentIdInformedItShouldBeDeleteStudent() throws StudentNotFoundException {

		// given
		StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		Student expectedStudent = studentMapper.toModel(expectedStudentDTO);

		// when
		when(studentRepository.findById(expectedStudentDTO.getId())).thenReturn(Optional.of(expectedStudent));
		doNothing().when(studentRepository).deleteById(expectedStudentDTO.getId());

		// then
		studentService.delete(expectedStudentDTO.getId());

		verify(studentRepository, times(1)).findById(expectedStudentDTO.getId());
		verify(studentRepository, times(1)).deleteById(expectedStudentDTO.getId());

	}

	@Test
	void whenStudentIdInformedItShouldBeUpdateStudent() throws StudentNotFoundException {

		// given
		StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		Student expectedStudent = studentMapper.toModel(expectedStudentDTO);
		MessageResponseDTO expectedMessageResponseDTO = studentService
				.createMessageResponseDTO("Student successfully updated with ID ", expectedStudent.getId());

		// when
		when(studentRepository.findById(expectedStudent.getId())).thenReturn(Optional.of(expectedStudent));
		when(studentRepository.save(expectedStudent)).thenReturn(expectedStudent);

		// then

		MessageResponseDTO messageResponseCreated = studentService.update(expectedStudentDTO.getId(),
				expectedStudentDTO);

		MatcherAssert.assertThat(expectedMessageResponseDTO.getMessage(),
				is(equalTo(messageResponseCreated.getMessage())));

	}

}
