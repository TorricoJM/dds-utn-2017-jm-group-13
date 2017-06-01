package model.parser.objetosParser;

import repositories.RepositorioEmpresas;

public class CuentaParser implements ExpresionParser {
	
	public String nombre;
	
	public double operar(String empresaEvaluada, String periodoEvaluado){
		return RepositorioEmpresas
				.obtenerValorDeCuentaDeEmpresaEnPeriodo(nombre, empresaEvaluada, periodoEvaluado);
	}
}