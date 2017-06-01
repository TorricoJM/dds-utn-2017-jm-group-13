package model.parser;

import java.util.LinkedList;
import java.util.List;

import model.parser.objetosParser.ExpresionParser;
import model.Indicador;

public class ObjetosParserStrategy implements TipoParserStrategy{
	
	public List<ExpresionParser> expresiones = new LinkedList<>();

	public double evaluarIndicador(Indicador indicador, String empresaEvaluada, String periodoEvaluado){
		if (expresiones.isEmpty()){
			
			this.generarArbolExpresiones(indicador);
		
		}
		
		return this.evaluarArbolExpresiones(empresaEvaluada, periodoEvaluado);
	}

	private double evaluarArbolExpresiones(String empresaEvaluada, String periodoEvaluado) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void generarArbolExpresiones(Indicador indicador) {
		// TODO Auto-generated method stub
		
	}
}