package model.parser.objetosParser;

import repositories.RepositorioIndicadores;

public class IndicadorParser implements ExpresionParser{
	
	public String nombre;
	
	public double operar(String empresaEvaluada, String periodoEvaluado){
		return RepositorioIndicadores.
				obtenerIndicadorDesdeNombre(nombre).
				evaluateEn(empresaEvaluada,periodoEvaluado);
	}
}