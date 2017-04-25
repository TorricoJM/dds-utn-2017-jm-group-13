package model;

import java.util.ArrayList;
import java.util.List;

public class ListaEmpresas {
	
	private List<Empresa> empresas = new ArrayList<>();

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
