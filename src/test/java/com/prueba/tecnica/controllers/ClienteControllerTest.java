package com.prueba.tecnica.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.prueba.tecnica.PruebaTecnicaApplication;
import com.prueba.tecnica.entities.ClienteDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.services.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = PruebaTecnicaApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
public class ClienteControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ClienteService clienteService;
	
	@Test
	public void consultarTestExito()
	  throws Exception {

		List<ClienteDO> list = new ArrayList<>();
		ClienteDO c = new ClienteDO();
		c.setNombre("Jose Lema");
		list.add(c);
		
		Mockito.when(clienteService.consultar()).thenReturn(list);
		
	    mvc.perform(get("/clientes")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].nombre", is("Jose Lema")));
	}
	
	@Test(expected = Exception.class)
	public void consultarTestException()
	  throws Exception {

		Mockito.when(clienteService.consultar()).thenThrow(new PruebaTecnicaException("Error"));
				
	    mvc.perform(get("/clientes")
	      .contentType(MediaType.APPLICATION_JSON));
	}
}
