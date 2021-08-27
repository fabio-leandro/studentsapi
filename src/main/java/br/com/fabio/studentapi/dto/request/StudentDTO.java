package br.com.fabio.studentapi.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.fabio.studentapi.entities.Phone;

public class StudentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	private String name;
	
	@NotEmpty
	private String cpf;
	
	@NotEmpty
	private LocalDate birthDate;
	
	@NotEmpty
	private List<Phone> phones;

}
