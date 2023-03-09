package com.prueba.tecnica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.prueba.tecnica.entities.ClienteDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<ClienteDO> consultar() throws PruebaTecnicaException {
		
		return clienteRepository.findAll();
	}
	
	public ClienteDO crear(ClienteDO cliente) throws PruebaTecnicaException {
		
		ClienteDO response = clienteRepository.getByIdCliente(cliente.getIdCliente());
		
		if(response != null) {
			throw new PruebaTecnicaException("El ID del cliente "+response.getIdCliente()+" ya existe");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public ClienteDO editar(ClienteDO cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public ClienteDO actualizar(@RequestBody ClienteDO cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public void eliminar(@RequestBody ClienteDO cliente) {
		
		clienteRepository.deleteById(cliente.getId());
	}
}
