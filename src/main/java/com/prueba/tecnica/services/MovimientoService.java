package com.prueba.tecnica.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.dtos.MovimientoDTO;
import com.prueba.tecnica.dtos.MovimientoFechaDTO;
import com.prueba.tecnica.entities.CuentaDO;
import com.prueba.tecnica.entities.MovimientoDO;
import com.prueba.tecnica.exceptions.PruebaTecnicaException;
import com.prueba.tecnica.repositories.CuentaRepository;
import com.prueba.tecnica.repositories.MovimientoRepository;

@Service
public class MovimientoService {

	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;
	
	public List<MovimientoDO> consultar() {
		
		return movimientoRepository.findAll();
	}

	public void crear(MovimientoDTO movimiento) throws PruebaTecnicaException {
		
		CuentaDO cuenta = cuentaRepository.getByNumeroCuenta(movimiento.getNumeroCuenta());
		
		if(cuenta == null) {
			throw new PruebaTecnicaException("La cuenta "+movimiento.getNumeroCuenta()+" no existe");
		}
		
		BigDecimal saldo = BigDecimal.ZERO;
		
		List<MovimientoDO> movimientos = movimientoRepository.findMovimientoByNumeroCuenta(movimiento.getNumeroCuenta());
		
		if(movimientos.isEmpty()) {
			saldo = cuenta.getSaldoInicial();
		} else {
			saldo = movimientos.get(0).getSaldo();
		}
		
		if(movimiento.getValor().compareTo(BigDecimal.ZERO) < 0 && saldo.compareTo(BigDecimal.ZERO) <= 0) {
			throw new PruebaTecnicaException("Saldo no disponible");
		}
		
		saldo = saldo.add(movimiento.getValor());
		
		movimientoRepository.saveByIdCliente(movimiento.getFecha(), movimiento.getTipoMovimiento(), 
				movimiento.getValor(), saldo, movimiento.getNumeroCuenta());
	}
	
	public List<MovimientoFechaDTO> findByFechaAndIdCliente(Date fechaInicio, Date fechaFin, Long idCliente) {
		List<Object[]> response = movimientoRepository.findByFechaAndIdCliente(fechaInicio, fechaFin, idCliente);
		
		List<MovimientoFechaDTO> movimientos = new ArrayList<>();
		
		response.forEach(mov -> {
			MovimientoFechaDTO movimiento = new MovimientoFechaDTO();
			movimiento.setFecha((Date) mov[0]);
			movimiento.setMovimiento((BigDecimal)mov[2]);
			movimiento.setSaldoDisponible((BigDecimal)mov[3]);
			movimiento.setCliente((String)mov[4]);
			movimiento.setNumeroCuenta((BigInteger)mov[5]);
			movimiento.setTipo((String)mov[6]);
			movimiento.setEstado((Boolean)mov[7]);
			movimiento.setSaldoInicial((BigDecimal)mov[8]);
			movimientos.add(movimiento);
		});
		
		return movimientos;
	}
}
