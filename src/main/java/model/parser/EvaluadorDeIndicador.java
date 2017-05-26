package model.parser;
import repositories.*;

public class EvaluadorDeIndicador {
	
	private TipoParserStrategy evaluadorStrategy = new Antlr4ParserStrategy();
	private String indicador;
	private String empresaEvaluada;
	private String periodoEvaluado;
	
	public String getIndicador(){
		return this.indicador;
	}
	
	public String getEmpresaEvaluada(){
		return this.empresaEvaluada;
	}
	
	public String getPeriodoEvaluado(){
		return this.periodoEvaluado;
	}
	
	public double evaluarIndicador(String indicador, String empresaNombre, String periodo){
		this.indicador = indicador;
		this.empresaEvaluada = empresaNombre;
		this.periodoEvaluado = periodo;
		return evaluadorStrategy.evaluarIndicador(this);
	}
	
	public double obtenerValor(String cuentaOIndicador){
		if (RepositorioEmpresas.obtenerEmpresaDesdeNombre(empresaEvaluada)
				.obtenerPeriodoDesdeNombre(this.getPeriodoEvaluado())
				.tieneCuenta(cuentaOIndicador))
			return Double.parseDouble(RepositorioEmpresas.obtenerEmpresaDesdeNombre(empresaEvaluada)
					.obtenerPeriodoDesdeNombre(this.getPeriodoEvaluado())
					.obtenerCuentaDesdeNombre(cuentaOIndicador)
					.getValor());
		else if (RepositorioIndicadores.tieneIndicador(cuentaOIndicador))
			return evaluarIndicador(cuentaOIndicador, this.getPeriodoEvaluado(), this.getPeriodoEvaluado());
		else 
			throw new ErrorEvaluacionException("No se ha encontrado la cuenta o indicador " + cuentaOIndicador);
	}
}