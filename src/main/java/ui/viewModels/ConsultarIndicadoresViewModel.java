package ui.viewModels;

import java.text.DecimalFormat;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Indicador;
import model.PeriodoFiscal;
import model.parser.EvaluadorDeIndicador;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

@Observable
public class ConsultarIndicadoresViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private PeriodoFiscal periodoSeleccionado;
	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	private String resultado;

	public ConsultarIndicadoresViewModel() {
		this.empresas = RepositorioEmpresas.all();
		this.indicadores = RepositorioIndicadores.all();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public PeriodoFiscal getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(PeriodoFiscal periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public void llamarEvaluador() {
		EvaluadorDeIndicador evaluador = new EvaluadorDeIndicador();
		Double resultadoIndicador = evaluador.evaluarIndicador(indicadorSeleccionado, empresaSeleccionada.getNombre(),
				periodoSeleccionado.getPeriodo());
		DecimalFormat formato = new DecimalFormat("###############.############");
		resultado = String.valueOf(formato.format(resultadoIndicador));
	}
}