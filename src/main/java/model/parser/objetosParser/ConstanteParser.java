package model.parser.objetosParser;

public class ConstanteParser implements ExpresionParser{
	
	public String constante;
	
	public double operar(String empresaEvaluada, String periodoEvaluado){
		return Double.parseDouble(constante);
	}
}