package model;

import com.opencsv.bean.CsvBindByName;

public class LineaEmpresa {
	@CsvBindByName(required = true)
	private String nombre;
	@CsvBindByName(required = true)
	private String periodo;
	@CsvBindByName(required = true)
	private String cuenta;
	@CsvBindByName(required = true)
	private String valor;
	
	
	public String getNombre() {
		return nombre;
	}
	public String getPeriodo() {
		return periodo;
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
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
