package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {

	private String nombre;
	private String periodoFiscal;
	private String cuenta;
	private String valor;
	
	public String getNombre() {
		return nombre;
	}
	public String getPeriodoFiscal() {
		return periodoFiscal;
	}
	public String getCuenta() {
		return cuenta;
	}
	public String getValor() {
		return valor;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPeriodoFiscal(String periodoFiscal) {
		this.periodoFiscal = periodoFiscal;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}