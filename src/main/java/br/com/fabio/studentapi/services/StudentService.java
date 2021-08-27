package br.com.fabio.studentapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabio.studentapi.dto.mapper.StudentMapper;
import br.com.fabio.studentapi.dto.request.StudentDTO;
import br.com.fabio.studentapi.dto.response.MessageResponseDTO;
import br.com.fabio.studentapi.entities.Student;
import br.com.fabio.studentapi.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {
	
	@Autowired
	private final StudentRepository studentRepository;
	
	@Autowired
	private final StudentMapper studentMapper;
	
	public MessageResponseDTO create(StudentDTO studentDTO) {
		Student student = studentMapper.toModel(studentDTO);
		Student salvedStudent = studentRepository.save(student);
		
		MessageResponseDTO messageResponse = 
				createMessageResponseDTO("Person successfully created with ID ", salvedStudent.getId());
		
		return messageResponse;
		
	}
	
	private MessageResponseDTO createMessageResponseDTO(String s, Long id2){
		return MessageResponseDTO.builder().message(s+id2).build();
	}

}
