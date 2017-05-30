package model.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import model.parser.antlr4parser.*;

public class Antlr4ParserStrategy implements TipoParserStrategy{
	
	@Override
	public double evaluarIndicador(EvaluadorDeIndicador evaluador){
/*		System.out.println(evaluador.getEmpresaEvaluada());
		System.out.println(evaluador.getPeriodoEvaluado());
		System.out.println(evaluador.getIndicador());
		System.out.println(evaluador.getIndicador().getNombre());
		System.out.println(evaluador.getIndicador().getOperacion());
*/
		ANTLRInputStream input = new ANTLRInputStream(evaluador.getIndicador().getOperacion());
    	IndicadorLexer lexer = new IndicadorLexer(input);
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	IndicadorParser parser = new IndicadorParser(tokens, evaluador);
    	//lexer.removeErrorListeners();
    	parser.setErrorHandler(new BailErrorStrategy());
    	//parser.removeErrorListeners();
    	return parser.eval().value;
	}
}