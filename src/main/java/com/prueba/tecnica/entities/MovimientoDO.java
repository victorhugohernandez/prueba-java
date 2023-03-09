package com.prueba.tecnica.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movimiento")
public class MovimientoDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fecha;
	
	private String tipoMovimiento;
	
	private BigDecimal valor;
	
	private BigDecimal saldo;
	
	@ManyToOne
	@JoinColumn(name = "numero_cuenta")
	private CuentaDO numeroCuenta;

	public MovimientoDO() {
	}
	
	public MovimientoDO(String tipoMovimiento, BigDecimal valor) {
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
	}
	
	public MovimientoDO(Long id, BigDecimal saldo) {
		this.id = id;
		this.saldo = saldo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public CuentaDO getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(CuentaDO numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}
