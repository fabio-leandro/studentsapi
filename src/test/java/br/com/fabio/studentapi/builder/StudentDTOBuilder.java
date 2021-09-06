package br.com.fabio.studentapi.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fabio.studentapi.dtos.request.PhoneDTO;
import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.enums.PhoneType;
import lombok.Builder;

@Builder
public class StudentDTOBuilder {
	
	@Builder.Default
	private Long id = 1L;
	
	@Builder.Default
	private String name = "Fabio";
	
	@Builder.Default
	private String cpf = "55566677788";;
	
	@Builder.Default
	private String birthDate = "12-02-1985";
	
	@Builder.Default
	private List<PhoneDTO> phones =
			new ArrayList<>(Arrays.asList(new PhoneDTO(1l,PhoneType.HOME,"(31)77777-9999")));

	
	public StudentDTO toStudentDTO() {
		return new StudentDTO(id, name, cpf,birthDate,phones);
	}
}
