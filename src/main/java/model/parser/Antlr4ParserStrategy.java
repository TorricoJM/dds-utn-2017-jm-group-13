package model.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import model.parser.antlr4parser.*;

public class Antlr4ParserStrategy implements TipoParserStrategy{
	
	@Override
	public double evaluarIndicador(EvaluadorDeIndicador evaluador){
		ANTLRInputStream input = new ANTLRInputStream(evaluador.getIndicador());
    	IndicadorLexer lexer = new IndicadorLexer(input);
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	IndicadorParser parser = new IndicadorParser(tokens, evaluador);
    	lexer.removeErrorListeners();
    	parser.setErrorHandler(new BailErrorStrategy());
    	System.out.println(parser.eval().value);
		return 0;
	}
}