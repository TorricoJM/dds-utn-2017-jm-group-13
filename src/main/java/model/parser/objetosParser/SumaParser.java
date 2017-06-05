package model.parser.objetosParser;

public class SumaParser implements ExpresionParser{
	
	public ExpresionParser op1;
	public ExpresionParser op2;
	
	public SumaParser(ExpresionParser op1, ExpresionParser op2) {
		this.op1 = op1;
		this.op2 = op2;
	}
	
	public double operar(String empresaEvaluada, String periodoEvaluado) {
		return op1.operar(empresaEvaluada, periodoEvaluado)+op2.operar(empresaEvaluada, periodoEvaluado);
	}
}