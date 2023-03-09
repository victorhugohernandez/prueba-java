package com.prueba.tecnica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.dtos.MovimientoDTO;
import com.prueba.tecnica.dtos.RespuestaDTO;
import com.prueba.tecnica.entities.MovimientoDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.services.MovimientoService;

@RestController
@RequestMapping("movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;
	
	@GetMapping
	public ResponseEntity<List<MovimientoDO>> consultar() {
		
		return ResponseEntity.ok(movimientoService.consultar());
	}
	
	@PostMapping
	public ResponseEntity<RespuestaDTO> crear(@RequestBody MovimientoDTO cliente) {
		
		RespuestaDTO res = new RespuestaDTO("Registro almacenado correctamente");
		
		try {
			movimientoService.crear(cliente);
			return ResponseEntity.ok(res);
		} catch (PruebaTecnicaException e) {
			res.setMensaje(e.getMessage());
			return ResponseEntity.status(500).body(res);
		}
		
		
	}
}
