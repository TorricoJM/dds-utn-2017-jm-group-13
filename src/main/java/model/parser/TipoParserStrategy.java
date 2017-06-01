package model.parser;

import model.Indicador;

public interface TipoParserStrategy {
	public double evaluarIndicador(Indicador indicador, String empresaEvaluada, String periodoEvaluado);
}