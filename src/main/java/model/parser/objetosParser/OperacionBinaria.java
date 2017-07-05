package model.parser.objetosParser;

public class OperacionBinaria implements ExpresionParser{
	
	public Operador operador;
	public ExpresionParser operando1;
	public ExpresionParser operando2;
	
	public OperacionBinaria(Operador operador,ExpresionParser op1, ExpresionParser op2) {
		this.operador = operador;
		this.operando1 = op1;
		this.operando2 = op2;
	}
	
	public double operar(String empresaEvaluada, String periodoEvaluado) {
		return this.operador.aplicar(operando1.operar(empresaEvaluada, periodoEvaluado),
				operando2.operar(empresaEvaluada, periodoEvaluado));
	}
}