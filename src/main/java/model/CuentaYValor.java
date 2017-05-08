package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class CuentaYValor {
	private String cuenta;
	private String valor;

	public CuentaYValor(String cuenta, String valor) {
		this.cuenta = cuenta;
		this.valor = valor;
	}

	public String getCuenta() {
		return cuenta;
	}

	public String getValor() {
		return valor;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}