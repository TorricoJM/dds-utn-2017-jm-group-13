package model.parser;

public interface TipoParserStrategy {
	public double evaluarIndicador(String operacion, String empresaEvaluada, String periodoEvaluado);
}