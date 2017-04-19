package model;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresas {
	
	private List<Empresa> empresas = new ArrayList<>();

	public List<Empresa> getEmpresas() {
		return empresas;
	}

}
