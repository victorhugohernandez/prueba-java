package com.prueba.tecnica.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.entities.CuentaDO;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaDO, Long>{

	@Transactional
	@Modifying
	@Query(
	  value = 
	    "insert into cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, id_cliente) "
	    + "values (:numero_cuenta, :tipo_cuenta, :saldo_inicial, :estado, :id_cliente)",
	  nativeQuery = true)
	void saveByIdCliente(@Param("numero_cuenta") Long numeroCuenta,
			@Param("tipo_cuenta") String tipoCuenta,
			@Param("saldo_inicial") BigDecimal saldoInicial,
			@Param("estado") Boolean estado,
			@Param("id_cliente") Long idCliente);
	
	@Query("select new com.prueba.tecnica.entities.CuentaDO(c.saldoInicial) from CuentaDO c where numeroCuenta=?1")
	CuentaDO getByNumeroCuenta(Long numeroCuenta);
}
