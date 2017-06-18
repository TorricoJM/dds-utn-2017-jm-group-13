package ui.viewModels;

import java.text.DecimalFormat;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import indicators.DataIndicador;
import model.Empresa;
import model.PeriodoFiscal;
import model.parser.EmpresaPeriodoVacioException;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

@Observable
public class ConsultarIndicadoresViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private PeriodoFiscal periodoSeleccionado;
	private List<DataIndicador> indicadores;
	private DataIndicador indicadorSeleccionado;
	private String resultado;

	public ConsultarIndicadoresViewModel() {
		this.empresas = RepositorioEmpresas.getInstance().getListaEmpresas();
		this.indicadores = RepositorioIndicadores.getInstance().getIndicadores();
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

	public List<DataIndicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<DataIndicador> indicadores) {
		this.indicadores = indicadores;
	}

	public DataIndicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(DataIndicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public void llamarEvaluador() {
		try {
		Double resultadoIndicador = indicadorSeleccionado.evaluateEn(empresaSeleccionada.getNombre(), periodoSeleccionado.getPeriodo());
		DecimalFormat formato = new DecimalFormat("###############.############");
		resultado = String.valueOf(formato.format(resultadoIndicador));
		} catch (NullPointerException e){
			throw new EmpresaPeriodoVacioException("Elegir empresa y periodo a evaluar");
		}
	}
}
