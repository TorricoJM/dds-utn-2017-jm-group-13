package model.parser;

import indicators.Indicador;

public interface TipoParserStrategy {
	public double evaluarIndicador(Indicador indicador, String empresaEvaluada, String periodoEvaluado);
}