package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import methodologies.Metodologia;
import model.Empresa;
import model.Exception;
import repositories.RepositorioEmpresas;
import repositories.RepositorioMetodologias;

@Observable
public class ConsultarMetodologiasViewModel {
	private List<Metodologia> metodologias;
	private Metodologia metodologiaSeleccionada;
	private String periodoInicioSeleccionado;
	private String periodoFinSeleccionado;
	private List<String> periodos;
	private List<Empresa> empresas;
	private List<Empresa> empresasResultantes;
	private List<String> periodosSeleccionados;

	public ConsultarMetodologiasViewModel() {
		this.metodologias = RepositorioMetodologias.getInstance().getListaMetodologias();
		this.empresas = new LinkedList<>(RepositorioEmpresas.getInstance().getListaEmpresas());
		this.periodos = empresas.stream().flatMap(empresa -> empresa.getPeriodos().stream()).map(periodo -> periodo.getPeriodo()).distinct().sorted().collect(Collectors.toList());
	}
	
	public void construirRangoDePeriodos(){
		if(this.periodoInicioSeleccionado.equals(this.periodoFinSeleccionado)){
			this.periodosSeleccionados = new LinkedList<>();
			periodosSeleccionados.add(periodoInicioSeleccionado);
		}else{
			periodosSeleccionados = periodos.subList(periodos.indexOf(periodoInicioSeleccionado), periodos.indexOf(periodoFinSeleccionado)+1);
		}
	}
	
	public void evaluarMetodologia() {
		if (this.metodologiaSeleccionada == null) {
			throw new Exception("Debe seleccionar una metodolog�a.");
		} else if (this.periodoInicioSeleccionado == null || this.periodoFinSeleccionado == null) {
			throw new Exception("Debe seleccionar un periodo de inicio y de fin.");
		} else if (Integer.parseInt(periodoInicioSeleccionado) > Integer.parseInt(periodoFinSeleccionado))
			throw new Exception("El periodo de inicio debe ser menor o igual que el de fin.");
		else {
		this.construirRangoDePeriodos();
		empresasResultantes = metodologiaSeleccionada.aplicarMetodologia(empresas, periodosSeleccionados);
		ObservableUtils.firePropertyChanged(this,"empresasResultantes");
		}
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	public String getPeriodoInicioSeleccionado() {
		return periodoInicioSeleccionado;
	}

	public void setPeriodoInicioSeleccionado(String periodoInicioSeleccionado) {
		this.periodoInicioSeleccionado = periodoInicioSeleccionado;
	}

	public String getPeriodoFinSeleccionado() {
		return periodoFinSeleccionado;
	}

	public void setPeriodoFinSeleccionado(String periodoFinSeleccionado) {
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
