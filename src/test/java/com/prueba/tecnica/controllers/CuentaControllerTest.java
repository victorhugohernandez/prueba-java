package com.prueba.tecnica.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.prueba.tecnica.entities.CuentaDO;
import com.prueba.tecnica.repositories.CuentaRepository;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(
		  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		  classes = PruebaTecnicaApplication.class)
@TestPropertySource(locations = "classpath:application_test.properties")
public class CuentaControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private CuentaRepository cuentaRepository;
	
	@Test
	public void consultarExitoTest() throws Exception {
		
		List<CuentaDO> list = new ArrayList<>();
		CuentaDO c = new CuentaDO();
		c.setNumeroCuenta(1234l);
		list.add(c);
		
		Mockito.when(cuentaRepository.findAll()).thenReturn(list);
		
	    mvc.perform(get("/cuentas")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].numeroCuenta", is(1234)));
	}
	
	@Test
	public void crearExitoTest() throws Exception {
		
	    mvc.perform(post("/cuentas")
	      .contentType(MediaType.APPLICATION_JSON).content("{\"estado\": false,\r\n" + 
	      		"  \"idCliente\": 445522,\r\n" + 
	      		"  \"numeroCuenta\": 9998,\r\n" + 
	      		"  \"saldoInicial\": 0,\r\n" + 
	      		"  \"tipoCuenta\": \"Corriente\"}"))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$.mensaje", is("Registro almacenado correctamente")));
	}

}
