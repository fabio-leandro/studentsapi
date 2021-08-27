package br.com.fabio.studentapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.fabio.studentapi.dto.request.StudentDTO;
import br.com.fabio.studentapi.entities.Student;



@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Student toModel(StudentDTO studentDTO);

    StudentDTO toDTO(Student student);

}
