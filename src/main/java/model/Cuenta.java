package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	private String nombre;
	private String valor;

	public Cuenta(String cuenta, String valor) {
		this.nombre = cuenta;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setNombre(String cuenta) {
		this.nombre = cuenta;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}