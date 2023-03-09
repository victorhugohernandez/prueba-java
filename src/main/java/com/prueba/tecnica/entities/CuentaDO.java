package com.prueba.tecnica.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta")
public class CuentaDO {
	
	@Id
	private Long numeroCuenta;
	
	private String tipoCuenta;
	
	private BigDecimal saldoInicial;
	
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private ClienteDO idCliente;

	public CuentaDO() {
		
	}
	
	public CuentaDO(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public ClienteDO getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(ClienteDO idCliente) {
		this.idCliente = idCliente;
	}
	
}
