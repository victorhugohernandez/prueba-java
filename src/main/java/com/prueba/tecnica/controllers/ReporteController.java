package com.prueba.tecnica.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.dtos.MovimientoFechaDTO;
import com.prueba.tecnica.dtos.RespuestaDTO;
import com.prueba.tecnica.services.MovimientoService;
import com.prueba.tecnica.services.ReporteService;

@RestController
@RequestMapping("reportes")
public class ReporteController {
	
	@Autowired
	private MovimientoService movimientoService;
	
	@Autowired
	private ReporteService reporteService;
	
	@GetMapping
	public ResponseEntity<List<MovimientoFechaDTO>> consultar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, 
			@RequestParam Long idCliente) {
		
		return ResponseEntity.ok(movimientoService.findByFechaAndIdCliente(fechaInicio, fechaFin, idCliente));
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<RespuestaDTO> pdf(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, 
			@RequestParam Long idCliente) {
		
		List<MovimientoFechaDTO> movimientos = movimientoService.findByFechaAndIdCliente(fechaInicio, fechaFin, idCliente);
		
		String pdf = reporteService.getPdf(movimientos);
		RespuestaDTO res = new RespuestaDTO(pdf);
		
		return ResponseEntity.ok(res);
	}

}
