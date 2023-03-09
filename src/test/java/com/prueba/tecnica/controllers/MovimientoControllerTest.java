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
import com.prueba.tecnica.entities.MovimientoDO;
import com.prueba.tecnica.services.MovimientoService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(
		  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		  classes = PruebaTecnicaApplication.class)
@TestPropertySource(locations = "classpath:application_test.properties")
public class MovimientoControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private MovimientoService movimientoService;
	
	@Test
	public void consultarExitoTest() throws Exception {
		
		List<MovimientoDO> list = new ArrayList<>();
		MovimientoDO c = new MovimientoDO();
		c.setTipoMovimiento("abono");
		list.add(c);
		
		Mockito.when(movimientoService.consultar()).thenReturn(list);
		
	    mvc.perform(get("/movimientos")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].tipoMovimiento", is("abono")));
	}
	
	@Test
	public void crearExitoTest() throws Exception {
		
	    mvc.perform(post("/movimientos")
	      .contentType(MediaType.APPLICATION_JSON).content("{\"fecha\": \"2022-11-17\",\r\n" + 
	      		"  \"numeroCuenta\": 9998,\r\n" + 
	      		"  \"tipoMovimiento\": \"Deposito\",\r\n" + 
	      		"  \"valor\": -15}"))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$.mensaje", is("Registro almacenado correctamente")));
	}
}
