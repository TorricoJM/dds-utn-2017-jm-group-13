package model;

import java.util.LinkedList;
import java.util.List;

public class Empresa {

	private String nombre;
	private List<PeriodoFiscalDeEmpresa> periodosFiscales = new LinkedList<>();

	
	public List<PeriodoFiscalDeEmpresa> getPeriodosFiscales() {
		return periodosFiscales;
	}
	public void setPeriodosFiscales(List<PeriodoFiscalDeEmpresa> periodosFiscales) {
		this.periodosFiscales = periodosFiscales;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}