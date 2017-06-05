package model.parser.objetosParser;

public class RestaParser implements ExpresionParser{

	public ExpresionParser op1;
	public ExpresionParser op2;
	
	public RestaParser(ExpresionParser op1, ExpresionParser op2) {
		this.op1 = op1;
		this.op2 = op2;
	}
	
	public double operar(String empresaEvaluada, String periodoEvaluado) {
		return op1.operar(empresaEvaluada, periodoEvaluado)-op2.operar(empresaEvaluada, periodoEvaluado);
	}
	
}