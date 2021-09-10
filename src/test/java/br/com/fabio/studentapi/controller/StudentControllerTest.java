package br.com.fabio.studentapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fabio.studentapi.builder.StudentDTOBuilder;
import br.com.fabio.studentapi.controllers.StudentController;
import br.com.fabio.studentapi.dtos.request.StudentDTO;
import br.com.fabio.studentapi.dtos.response.MessageResponseDTO;
import br.com.fabio.studentapi.services.StudentService;
import br.com.fabio.studentapi.util.JsonConvertionUtil;


@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	
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
	                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
	                .build();
	    }
	
	
	
	@Test
    void whenPOSTIsCalledThenAStudentIsCreated() throws Exception {
        // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
       MessageResponseDTO messageResponseDTO = 
    		   MessageResponseDTO.builder().message("Student successfully created with ID " + studentDTO.getId()).build();
        
       when(studentService.create(studentDTO)).thenReturn(messageResponseDTO);
       
       
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtil.asJsonString(studentDTO)))
        		.andExpect(status().isCreated());
                
                
    }

}
