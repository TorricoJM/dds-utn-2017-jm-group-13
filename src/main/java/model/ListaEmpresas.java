package model;

import java.util.LinkedList;
import java.util.List;

public class ListaEmpresas {
	
	private List<Empresa> empresas = new LinkedList<>();

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
