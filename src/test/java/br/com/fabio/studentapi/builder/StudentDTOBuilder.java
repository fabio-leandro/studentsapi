package br.com.fabio.studentapi.builder;

import br.com.fabio.studentapi.dtos.request.StudentDTO;
import lombok.Builder;

@Builder
public class StudentDTOBuilder {
	
	
	@Builder.Default
	private Long id = 30L;
	
	@Builder.Default
	private String name = "Fabio";
	
	@Builder.Default
	private String cpf = "55566677788";;
	
//	@Builder.Default
//	private String birthDate = "12-02-1985";
	
//	@Builder.Default
//	private List<PhoneDTO> phones =
//			new ArrayList<>(Arrays.asList(new PhoneDTO(1L,PhoneType.HOME,"(31)77777-9999")));

	
	public StudentDTO toStudentDTO() {
		return new StudentDTO(id,name, cpf);
	}
	
	
	
}
