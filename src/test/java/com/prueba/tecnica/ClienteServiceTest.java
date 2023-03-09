package com.prueba.tecnica;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.tecnica.entities.ClienteDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.repositories.ClienteRepository;
import com.prueba.tecnica.services.ClienteService;

@SpringBootTest
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
    private ClienteService clienteService;
	
	@Test
	public void consultarTest() throws PruebaTecnicaException {
		
		when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
		
		List<ClienteDO> clientes = clienteService.consultar();
		
		assertNotNull(clientes);
	}
	
	@Test
	public void crearTest() throws PruebaTecnicaException {
		
		ClienteDO cliente = new ClienteDO();
		cliente.setIdCliente(1l);
		
		when(clienteRepository.getByIdCliente(1l)).thenReturn(null);
		
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		
		ClienteDO response = clienteService.crear(cliente);
		
		assertNotNull(response);
	}
	
	@Test
	public void crearTestException() throws PruebaTecnicaException {
		
		ClienteDO cliente = new ClienteDO();
		cliente.setIdCliente(1l);
		
		when(clienteRepository.getByIdCliente(1l)).thenReturn(cliente);
		
		assertThrows(PruebaTecnicaException.class, () -> clienteService.crear(cliente));
	}
}
