// Generated from Indicador.g4 by ANTLR 4.7
package model.parser.antlr4parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IndicadorParser}.
 */
public interface IndicadorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#eval}.
	 * @param ctx the parse tree
	 */
	void enterEval(IndicadorParser.EvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#eval}.
	 * @param ctx the parse tree
	 */
	void exitEval(IndicadorParser.EvalContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#additionExp}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExp(IndicadorParser.AdditionExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#additionExp}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExp(IndicadorParser.AdditionExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#multiplyExp}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExp(IndicadorParser.MultiplyExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#multiplyExp}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExp(IndicadorParser.MultiplyExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicadorParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(IndicadorParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicadorParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(IndicadorParser.AtomExpContext ctx);
}