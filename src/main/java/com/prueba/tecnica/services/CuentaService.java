package com.prueba.tecnica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.dtos.CuentaDTO;
import com.prueba.tecnica.entities.CuentaDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.repositories.CuentaRepository;

@Service
public class CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;
	
	public CuentaDTO crear(CuentaDTO cuenta) throws PruebaTecnicaException {
		CuentaDO c = cuentaRepository.getByNumeroCuenta(cuenta.getNumeroCuenta());
		
		if(c != null) {
			throw new PruebaTecnicaException("La cuenta "+cuenta.getNumeroCuenta()+" ya existe");
		}
		
		cuentaRepository.saveByIdCliente(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta(), cuenta.getSaldoInicial(), 
				cuenta.getEstado(), cuenta.getIdCliente());
		
		return cuenta;
	}
}
