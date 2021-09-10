package br.com.fabio.studentapi.dtos.request;

import java.time.LocalDate;
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
	@Size(min = 11, max = 11, message = "O CPF deve conter 11  digitos sem caracteres especiais.")
	private String cpf;
	
	@NotNull
	private LocalDate birthDate;
	
	@NotEmpty
	private List<PhoneDTO> phones;
	
	
	

}
