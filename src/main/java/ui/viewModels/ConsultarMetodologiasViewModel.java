package ui.viewModels;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import methodologies.DataMetodologia;
import model.Empresa;
import model.PeriodoFiscal;
import repositories.RepositorioEmpresas;
import repositories.RepositorioMetodologias;

@Observable
public class ConsultarMetodologiasViewModel {
	private List<DataMetodologia> metodologias;
	private DataMetodologia metodologiaSeleccionada;
	private PeriodoFiscal periodoInicioSeleccionado;
	private PeriodoFiscal periodoFinSeleccionado;
	private List<String> periodos;
	private List<Empresa> empresas;
	private List<Empresa> empresasResultantes;
	private List<String> periodosSeleccionados;

	public ConsultarMetodologiasViewModel() {
		this.metodologias = RepositorioMetodologias.getInstance().getListaMetodologias();
		this.empresas = RepositorioEmpresas.getInstance().getListaEmpresas();
		this.periodos = empresas.stream().flatMap(empresa -> empresa.getPeriodos().stream()).map(periodo -> periodo.getPeriodo()).distinct().collect(Collectors.toList());
		//TODO Sacar periodos repetidos y ordenar de menor a mayor
	}
	
	public void evaluarMetodologia() {
		empresasResultantes = metodologiaSeleccionada.aplicarMetodologia(empresas, periodosSeleccionados);
		ObservableUtils.firePropertyChanged(this,"empresasResultantes");
	}

	public List<DataMetodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<DataMetodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public DataMetodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(DataMetodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	public PeriodoFiscal getPeriodoInicioSeleccionado() {
		return periodoInicioSeleccionado;
	}

	public void setPeriodoInicioSeleccionado(PeriodoFiscal periodoInicioSeleccionado) {
		this.periodoInicioSeleccionado = periodoInicioSeleccionado;
	}

	public PeriodoFiscal getPeriodoFinSeleccionado() {
		return periodoFinSeleccionado;
	}

	public void setPeriodoFinSeleccionado(PeriodoFiscal periodoFinSeleccionado) {
		this.periodoFinSeleccionado = periodoFinSeleccionado;
	}

	public List<String> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Empresa> getEmpresasResultantes() {
		return empresasResultantes;
	}

	public void setEmpresasResultantes(List<Empresa> empresasResultantes) {
		this.empresasResultantes = empresasResultantes;
	}	
}
