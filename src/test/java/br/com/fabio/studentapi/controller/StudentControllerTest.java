package br.com.fabio.studentapi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fabio.studentapi.builder.StudentDTOBuilder;
import br.com.fabio.studentapi.controllers.StudentController;
import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.dtos.response.MessageResponseDTO;
import br.com.fabio.studentapi.exceptions.StudentNotFoundException;
import br.com.fabio.studentapi.services.StudentService;
import br.com.fabio.studentapi.util.JsonConvertionUtil;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

	private static final long STUDENT_ID_INVALID = 2L;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((s, locale) -> new MappingJackson2JsonView()).build();
	}

	@Test
	void whenPOSTIsCalledThenAStudentIsCreated() throws Exception {
		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		MessageResponseDTO messageResponseDTO = MessageResponseDTO.builder()
				.message("Student successfully created with ID " + studentDTO.getId()).build();
		// when
		when(studentService.create(studentDTO)).thenReturn(messageResponseDTO);

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students").contentType(MediaType.APPLICATION_JSON)
				.content(JsonConvertionUtil.asJsonString(studentDTO))).andExpect(status().isCreated());

	}

	@Test
	void whenGetCalledAListOfStudentsItShouldBeRetornedOkStatus() throws Exception {

		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();

		// when
		when(studentService.listAll()).thenReturn(Collections.singletonList(studentDTO));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void whenGetCalledWithIdInformedItShouldBeRetornedOkStatus() throws Exception {

		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();

		// when
		when(studentService.findById(studentDTO.getId())).thenReturn(studentDTO);

		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/" + studentDTO.getId())
				.content(JsonConvertionUtil.asJsonString(studentDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is(studentDTO.getName())))
				.andExpect(jsonPath("$.cpf", is(studentDTO.getCpf())));

	}

	@Test
	void whenGetCalledWithIdInformedNotFoundItShouldRetornedStudentNotFound() throws Exception {

		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();

		// when
		when(studentService.findById(studentDTO.getId())).thenThrow(StudentNotFoundException.class);

		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/" + studentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	void whenDeleteStudentIdInformedCalledThenItShouldBeNoContentStatus() throws Exception {

		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();

		// when
		doNothing().when(studentService).delete(studentDTO.getId());

		// then
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/students/" + studentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}

	@Test
	void whenDeleteCalledWithIdInformedNotFoundItShouldRetornedStudentNotFound() throws Exception {

		// when
		doThrow(StudentNotFoundException.class).when(studentService).delete(STUDENT_ID_INVALID);

		// then
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/students/" + STUDENT_ID_INVALID)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	void whenUpdateCalledIdShouldBeRetornedOkStatus() throws Exception {

		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
		MessageResponseDTO messageResponseDTO = MessageResponseDTO.builder()
				.message("Student update with ID " + studentDTO.getId()).build();
		// when
		when(studentService.update(studentDTO.getId(), studentDTO)).thenReturn(messageResponseDTO);

		// then
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/students/" + studentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).content(JsonConvertionUtil.asJsonString(studentDTO)))
				.andExpect(status().isOk());

	}

	@Test
	void whenUpdateCalledIdShouldBeRetornedBadRequest() throws Exception {
		// given
		StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();

		// when
		when(studentService.update(STUDENT_ID_INVALID, studentDTO)).thenThrow(StudentNotFoundException.class);

		// then
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/students/" + STUDENT_ID_INVALID)
				.contentType(MediaType.APPLICATION_JSON).content(JsonConvertionUtil.asJsonString(studentDTO)))
				.andExpect(status().isNotFound());

	}

}
