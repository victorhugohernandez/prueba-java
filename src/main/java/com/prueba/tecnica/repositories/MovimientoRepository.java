package com.prueba.tecnica.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.entities.MovimientoDO;

public interface MovimientoRepository extends JpaRepository<MovimientoDO, Long>{

	@Query(value = "SELECT m.fecha, m.tipo_movimiento, m.valor, m.saldo, p.nombre, c.numero_cuenta, c.tipo_cuenta, c.estado, c.saldo_inicial "
			+ "FROM movimiento m "
			+ "INNER JOIN cuenta c ON m.numero_cuenta=c.numero_cuenta "
			+ "INNER JOIN persona p ON c.id_cliente=p.id_cliente "
			+ "WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin AND c.id_cliente=:idCliente",
			nativeQuery = true)
	List<Object[]> findByFechaAndIdCliente(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("idCliente") Long idCliente);
	
	@Transactional
	@Modifying
	@Query(
	  value = 
	    "insert into movimiento (fecha, tipo_movimiento, valor, saldo, numero_cuenta) "
	    + "values (:fecha, :tipo_movimiento, :valor, :saldo, :numero_cuenta)",
	  nativeQuery = true)
	void saveByIdCliente(@Param("fecha") Date fecha,
			@Param("tipo_movimiento") String tipoMovimiento,
			@Param("valor") BigDecimal valor,
			@Param("saldo") BigDecimal saldo,
			@Param("numero_cuenta") Long numeroCuenta);
	
	@Query("select new com.prueba.tecnica.entities.MovimientoDO(m.id, m.saldo) from MovimientoDO m where numeroCuenta.numeroCuenta=?1 order by id desc")
	List<MovimientoDO> findMovimientoByNumeroCuenta(Long numeroCuenta);
}
