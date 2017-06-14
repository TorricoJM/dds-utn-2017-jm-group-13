package model.parser.objetosParser;

import repositories.RepositorioIndicadores;

public class IndicadorParser implements ExpresionParser{
	
	public String nombre;
	
	public IndicadorParser(String id) {
		this.nombre = id;
	}

	public double operar(String empresaEvaluada, String periodoEvaluado){
		return RepositorioIndicadores.getInstance().
				obtenerIndicadorDesdeNombre(nombre).
				evaluateEn(empresaEvaluada,periodoEvaluado);
	}
}