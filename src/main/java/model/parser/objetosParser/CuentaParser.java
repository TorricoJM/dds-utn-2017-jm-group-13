package model.parser.objetosParser;

import repositories.RepositorioEmpresas;

public class CuentaParser implements ExpresionParser {
	
	public String nombre;
	
	public CuentaParser(String id) {
		this.nombre = id;
	}

	public double operar(String empresaEvaluada, String periodoEvaluado){
		return RepositorioEmpresas.getInstance()
				.obtenerValorDeCuentaDeEmpresaEnPeriodo(nombre, empresaEvaluada, periodoEvaluado);
	}
}