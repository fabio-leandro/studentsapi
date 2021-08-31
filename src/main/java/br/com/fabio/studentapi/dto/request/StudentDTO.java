package br.com.fabio.studentapi.dto.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fabio.studentapi.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	
	
	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	private String name;
	
	@NotEmpty
	private String cpf;
	
	@NotNull
	private String birthDate;
	
	@NotEmpty
	private List<Phone> phones;

}
