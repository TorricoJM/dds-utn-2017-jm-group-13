package ui.viewModels;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import indicators.Indicador;
import model.Empresa;
import model.PeriodoFiscal;
import model.parser.ErrorEvaluacionException;
import repositories.repoArchivos.RepositorioEmpresas;
import repositories.repoArchivos.RepositorioIndicadores;

@Observable
public class ConsultarIndicadoresViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private PeriodoFiscal periodoSeleccionado;
	private List<Indicador> indicadores;
	private List<String> resultados;

	public ConsultarIndicadoresViewModel() {
		this.empresas = RepositorioEmpresas.getInstance().getElementos();
		this.indicadores = RepositorioIndicadores.getInstance().getElementos();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.construirListaResultados();
	}

	public PeriodoFiscal getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(PeriodoFiscal periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
		this.construirListaResultados();
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	
	public boolean getEnabled() {
		return false;
	}


	private void construirListaResultados() {
		resultados = new LinkedList<>();
		
		if(empresaSeleccionada != null && periodoSeleccionado != null) {
			resultados = indicadores.stream().map((i) -> this.evaluar(i)).collect(Collectors.toList());
			ObservableUtils.firePropertyChanged(this, "resultados");
		}
	}
	
	public List<String> getResultados() {
		return resultados;
	}

	private String evaluar(Indicador unIndicador) {
		try {
			Double resultadoIndicador = unIndicador.evaluateEn(empresaSeleccionada.getNombre(),periodoSeleccionado.getPeriodo());
			DecimalFormat formato = new DecimalFormat("###############.############");
			
			return String.valueOf(formato.format(resultadoIndicador));
		}
		catch (ErrorEvaluacionException e) {
			return "No se pudo resolver";	//no retorna nada
		}
		catch (NullPointerException e) {
			return "Null Pointer";
		}
	}
}
