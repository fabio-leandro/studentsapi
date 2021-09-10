package br.com.fabio.studentapi.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fabio.studentapi.dtos.request.PhoneDTO;
import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.enums.PhoneType;
import lombok.Builder;

@Builder
public class StudentDTOBuilder {
	
	private static final Long ID_PHONE = 1L;
	private static final PhoneType TYPE_PHONE = PhoneType.HOME;
	private static final String NUMBER_PHONE = "(31)77777-9999";
	
	
	@Builder.Default
	private Long id = 30L;
	
	@Builder.Default
	private String name = "Fabio";
	
	@Builder.Default
	private String cpf = "55566677788";
	
	@Builder.Default
	private LocalDate birthDate = LocalDate.of(1985, 12, 02);
	
	@Builder.Default
	private List<PhoneDTO> phoneDTOs = 
			new ArrayList<>(Arrays.asList(new PhoneDTO(ID_PHONE,TYPE_PHONE,NUMBER_PHONE)));
	
	
	public StudentDTO toStudentDTO() {
		//this.birthDate = LocalDate.of(1985, 12, 02);
		return new StudentDTO(id,name, cpf,birthDate, phoneDTOs);
	}
	
	
	
}
