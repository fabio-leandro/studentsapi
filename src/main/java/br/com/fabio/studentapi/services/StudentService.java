package br.com.fabio.studentapi.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabio.studentapi.dtos.mapper.StudentMapper;
import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.dtos.response.MessageResponseDTO;
import br.com.fabio.studentapi.entities.Student;
import br.com.fabio.studentapi.exceptions.StudentNotFoundException;
import br.com.fabio.studentapi.repositories.StudentRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	private final StudentMapper studentMapper = StudentMapper.INSTANCE;
	
	public MessageResponseDTO create(StudentDTO studentDTO) {
		Student student = studentMapper.toModel(studentDTO);
		Student salvedStudent = studentRepository.save(student);
		
		MessageResponseDTO messageResponse = 
				createMessageResponseDTO("Student successfully created with ID ", salvedStudent.getId());
		
		return messageResponse;
		
	}
	
	public List<StudentDTO> listAll(){
		List<Student> student = studentRepository.findAll();
		return student.stream().map(studentMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public StudentDTO findById(Long id) throws StudentNotFoundException{
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		return studentMapper.toDTO(student);
	}
	
	public void delete(Long id) throws StudentNotFoundException{
		studentRepository.findById(id)
		.orElseThrow(() -> new StudentNotFoundException(id));
		studentRepository.deleteById(id);
		
	}
	
	public MessageResponseDTO update(Long id, StudentDTO studentDTO) throws StudentNotFoundException {
		
		studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		
		Student student = studentMapper.toModel(studentDTO);
		Student salvedStudent = studentRepository.save(student);
		
		MessageResponseDTO messageResponse = 
				createMessageResponseDTO("Student successfully updated with ID ", salvedStudent.getId());
		
		return messageResponse;
		
	}
	
	
	
	public MessageResponseDTO createMessageResponseDTO(String s, Long id2){
		return MessageResponseDTO.builder().message(s+id2).build();
	}

	

	

}
