package model.parser;

import java.util.LinkedList;
import java.util.List;

import model.parser.objetosParser.ExpresionLexer;
import model.parser.objetosParser.ExpresionParser;

public class ObjetosParserStrategy implements TipoParserStrategy{
	
	public List<ExpresionParser> expresiones = new LinkedList<>();

	public double evaluarIndicador(String operacion, String empresaEvaluada, String periodoEvaluado){
		if (expresiones.isEmpty()){
			expresiones = new ExpresionLexer().generarArbolExpresiones(operacion);
		}
		return this.evaluarArbolExpresiones(empresaEvaluada, periodoEvaluado);
	}

	public double evaluarArbolExpresiones(String empresaEvaluada, String periodoEvaluado) {
		return expresiones.get(0).operar(empresaEvaluada, periodoEvaluado);
	}


}