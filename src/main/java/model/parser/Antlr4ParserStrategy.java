package model.parser;

import java.util.NoSuchElementException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;

import indicators.Indicador;
import model.parser.antlr4parser.*;
import repositories.RepositorioCuentas;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class Antlr4ParserStrategy implements TipoParserStrategy {
	
	private String empresaEvaluada;
	private String periodoEvaluado;

	public String getEmpresaEvaluada() {
		return this.empresaEvaluada;
	}

	public String getPeriodoEvaluado() {
		return this.periodoEvaluado;
	}

	
	public double evaluarIndicador(Indicador indicador, String empresaEvaluada, String periodoEvaluado) {
		this.empresaEvaluada = empresaEvaluada;
		this.periodoEvaluado = periodoEvaluado;
		ANTLRInputStream input = new ANTLRInputStream(indicador.getOperacion());
		IndicadorLexer lexer = new IndicadorLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		IndicadorParser parser = new IndicadorParser(tokens, this);
		parser.setErrorHandler(new BailErrorStrategy());
		return parser.eval().value;
	}
	
	public double obtenerValor(String cuentaOIndicador) {
		if (RepositorioCuentas.tieneCuenta(cuentaOIndicador))
			try {
			return RepositorioEmpresas.obtenerValorDeCuentaDeEmpresaEnPeriodo(cuentaOIndicador, this.empresaEvaluada, this.periodoEvaluado);
			} catch (NoSuchElementException e){
				throw new ErrorEvaluacionException("No se encuentra la cuenta: \"" + cuentaOIndicador + "\" para el periodo: " + this.getPeriodoEvaluado() + " de la empresa: " + this.getEmpresaEvaluada());
			}
		else if (RepositorioIndicadores.tieneIndicador(cuentaOIndicador))
			return RepositorioIndicadores.obtenerIndicadorDesdeNombre(cuentaOIndicador).evaluateEn(this.empresaEvaluada,this.periodoEvaluado);
		else
			throw new ErrorEvaluacionException("No se ha encontrado la cuenta o indicador " + cuentaOIndicador);
	}
}