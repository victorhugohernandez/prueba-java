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

import com.prueba.tecnica.dtos.RespuestaDTO;
import com.prueba.tecnica.entities.ClienteDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.services.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDO>> consultar() throws PruebaTecnicaException {
		
		return ResponseEntity.ok(clienteService.consultar());
	}
	
	@PostMapping
	public ResponseEntity<ClienteDO> crear(@RequestBody ClienteDO cliente) throws PruebaTecnicaException {
		
		ClienteDO response = clienteService.crear(cliente);
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping
	public ResponseEntity<ClienteDO> editar(@RequestBody ClienteDO cliente) {
		
		return ResponseEntity.ok(clienteService.editar(cliente));
	}
	
	@PutMapping
	public ResponseEntity<ClienteDO> actualizar(@RequestBody ClienteDO cliente) {
		
		return ResponseEntity.ok(clienteService.actualizar(cliente));
	}
	
	@DeleteMapping
	public ResponseEntity<RespuestaDTO> eliminar(@RequestBody ClienteDO cliente) {
		
		clienteService.eliminar(cliente);
		RespuestaDTO res = new RespuestaDTO("Registro eliminado correctamente");
		
		return ResponseEntity.ok(res);
	}

}
