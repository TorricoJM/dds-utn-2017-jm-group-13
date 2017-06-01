package model.parser.objetosParser;

public class SumaParser implements ExpresionParser{
	
	public ExpresionParser exp1;
	public ExpresionParser exp2;
	
	public double operar(String empresaEvaluada, String periodoEvaluado) {
		return exp1.operar(empresaEvaluada, periodoEvaluado)+exp2.operar(empresaEvaluada, periodoEvaluado);
	}
}