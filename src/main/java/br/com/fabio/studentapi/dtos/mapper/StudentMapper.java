package br.com.fabio.studentapi.dtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.entities.Student;



@Mapper
public interface StudentMapper {
	
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	 
	//@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Student toModel(StudentDTO studentDTO);

    StudentDTO toDTO(Student student);

}
