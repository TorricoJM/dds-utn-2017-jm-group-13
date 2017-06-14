package model.parser.objetosParser;

import model.parser.ObjetosParserStrategy;
import model.parser.TipoParserStrategy;

public class IndicadorParser implements ExpresionParser{
	
	public String nombre;
	public String operacion;
	public TipoParserStrategy parser;
	
	public IndicadorParser(String id, String operacion) {
		this.nombre = id;
		this.operacion = operacion;
		this.parser = new ObjetosParserStrategy();
	}

	public double operar(String empresaEvaluada, String periodoEvaluado){
		return parser.evaluarIndicador(this.operacion, empresaEvaluada, periodoEvaluado);
	}
}