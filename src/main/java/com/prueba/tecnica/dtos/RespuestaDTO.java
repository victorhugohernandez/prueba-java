package com.prueba.tecnica.dtos;

public class RespuestaDTO {

	private String mensaje;
	
	public RespuestaDTO(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
