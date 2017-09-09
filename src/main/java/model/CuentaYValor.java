package model;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class CuentaYValor {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String cuenta;
	private String valor;

	public CuentaYValor(String cuenta, String valor) {
		this.cuenta = cuenta;
		this.valor = valor;
	}
	
	public CuentaYValor() {
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