package com.prueba.tecnica.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
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
import com.prueba.tecnica.dtos.MovimientoFechaDTO;
import com.prueba.tecnica.services.MovimientoService;
import com.prueba.tecnica.services.ReporteService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(
		  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		  classes = PruebaTecnicaApplication.class)
@TestPropertySource(locations = "classpath:application_test.properties")
public class ReporteControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private MovimientoService movimientoService;
	
	@MockBean
	private ReporteService reporteService;
	
	@Test
	public void consultarExitoTest() throws Exception {
		
		List<MovimientoFechaDTO> list = new ArrayList<>();
		MovimientoFechaDTO c = new MovimientoFechaDTO();
		c.setTipo("Ahorro");
		list.add(c);
		
		Date fechaInicio = new Date(123, 0, 1);
		Date fechaFin = new Date(123, 2, 31);
		
		Mockito.when(movimientoService.findByFechaAndIdCliente(fechaInicio, fechaFin, 1l)).thenReturn(list);
		
	    mvc.perform(get("/reportes?fechaFin=2023-03-31&fechaInicio=2023-01-01&idCliente=1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].tipo", is("Ahorro")));
	}
	
	@Test
	public void pdfExitoTest() throws Exception {
		
		List<MovimientoFechaDTO> list = new ArrayList<>();
		MovimientoFechaDTO c = new MovimientoFechaDTO();
		c.setTipo("Ahorro");
		list.add(c);
		
		Date fechaInicio = new Date(123, 0, 1);
		Date fechaFin = new Date(123, 2, 31);
		
		Mockito.when(movimientoService.findByFechaAndIdCliente(fechaInicio, fechaFin, 1l)).thenReturn(list);
		
		Mockito.when(reporteService.getPdf(list)).thenReturn("reporteBase64");
		
	    mvc.perform(get("/reportes/pdf?fechaFin=2023-03-31&fechaInicio=2023-01-01&idCliente=1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$.mensaje", is("reporteBase64")));
	}
}
