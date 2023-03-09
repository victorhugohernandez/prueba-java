package com.prueba.tecnica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.dtos.CuentaDTO;
import com.prueba.tecnica.dtos.RespuestaDTO;
import com.prueba.tecnica.entities.CuentaDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.repositories.CuentaRepository;
import com.prueba.tecnica.services.CuentaService;

@RestController
@RequestMapping("cuentas")
public class CuentaController {

	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping
	public ResponseEntity<List<CuentaDO>> consultar() {
		
		return ResponseEntity.ok(cuentaRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<RespuestaDTO> crear(@RequestBody CuentaDTO cliente) throws PruebaTecnicaException {
		
		cuentaService.crear(cliente);

		RespuestaDTO res = new RespuestaDTO("Registro almacenado correctamente");
		
		return ResponseEntity.ok(res);
	}
	
	@PatchMapping
	public ResponseEntity<CuentaDO> editar(@RequestBody CuentaDO cliente) {
		
		CuentaDO response = cuentaRepository.save(cliente);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<CuentaDO> actualizar(@RequestBody CuentaDO cliente) {
		
		CuentaDO response = cuentaRepository.save(cliente);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping
	public ResponseEntity<RespuestaDTO> eliminar(@RequestBody CuentaDO cliente) throws PruebaTecnicaException {
		
		try {
			cuentaRepository.deleteById(cliente.getNumeroCuenta());
		} catch(Exception e) {
			throw new PruebaTecnicaException("No fue posible eliminar el registro");
		}
		RespuestaDTO res = new RespuestaDTO("Registro eliminado correctamente");
		
		return ResponseEntity.ok(res);
	}
}
