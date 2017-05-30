package model.parser;
import model.Indicador;
import repositories.*;

public class EvaluadorDeIndicador {
	
	private TipoParserStrategy evaluadorStrategy = new Antlr4ParserStrategy();
	private Indicador indicador;
	private String empresaEvaluada;
	private String periodoEvaluado;
	
	public Indicador getIndicador(){
		return this.indicador;
	}
	
	public String getEmpresaEvaluada(){
		return this.empresaEvaluada;
	}
	
	public String getPeriodoEvaluado(){
		return this.periodoEvaluado;
	}
	
	public double evaluarIndicador(Indicador indicador, String empresaNombre, String periodo){
		this.indicador = new Indicador("Ingreso neto", "operaciones continuas+operaciones discontinuas"); //acá iría el indicador que pasa por parametro pero llega null la operación dentro del indicador, revisar después
		this.empresaEvaluada = empresaNombre;
		this.periodoEvaluado = periodo;
		return evaluadorStrategy.evaluarIndicador(this);
	}
	
	public double obtenerValor(String cuentaOIndicador){
		if (RepositorioCuentas.tieneCuenta(cuentaOIndicador))
			return Double
					.parseDouble(RepositorioEmpresas.obtenerEmpresaDesdeNombre(this.getEmpresaEvaluada())
									.obtenerPeriodoDesdeNombre(this.getPeriodoEvaluado())
									.obtenerCuentaDesdeNombre(cuentaOIndicador)
									.getValor());
		else if (RepositorioIndicadores.tieneIndicador(cuentaOIndicador))
			return this.evaluarIndicador(RepositorioIndicadores.obtenerIndicadorDesdeNombre(cuentaOIndicador), this.getEmpresaEvaluada(), this.getPeriodoEvaluado());
		else 
			throw new ErrorEvaluacionException("No se ha encontrado la cuenta o indicador " + cuentaOIndicador);
	}
}