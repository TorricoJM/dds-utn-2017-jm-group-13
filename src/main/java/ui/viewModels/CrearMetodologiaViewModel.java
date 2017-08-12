package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import criterios.Criterio;
import criterios.CriterioTaxativo;
import methodologies.MetodologiesBuilder;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioMetodologias;

@Observable
public class CrearMetodologiaViewModel {

	private List<Criterio> criterios;
	private List<Double> ponderaciones = new LinkedList<>();
	private Criterio criterioSeleccionado;
	private String cuentaSeleccionada;
	private String nombre;
	private List<Pair<Criterio,Double>> criteriosPonderacionElegidos = new LinkedList<>();
	private Double ponderacionSeleccionada;

	public CrearMetodologiaViewModel() {
		this.criterios = RepositorioCriterios.getInstance().getCriterios();
	}

	public void agregarCriterio() {
		if (criterioSeleccionado == null) {
			throw new Exception("Seleccione un criterio");
		} else {
			this.discriminarCriterio();
			ObservableUtils.firePropertyChanged(this, "criteriosPonderacionElegidos");
			this.setCriterioSeleccionado(null);
		}
	}

	public void crearMetodologia() {
		if (criteriosPonderacionElegidos.size() == 0) {
			throw new Exception("Agregue criterios");
		} else {
			RepositorioMetodologias.getInstance()
					.agregar(new MetodologiesBuilder().setNombre(nombre).setCriteriosPonderacion(criteriosPonderacionElegidos).build());
		}
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}

	public Criterio getCriterioSeleccionado() {
		return criterioSeleccionado;
	}

	public void setCriterioSeleccionado(Criterio criterioSeleccionado) {
		this.criterioSeleccionado = criterioSeleccionado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Criterio> getCriteriosPonderacionElegidos() {
		return criteriosPonderacionElegidos.stream().map(pair -> pair.getValue0()).collect(Collectors.toList());
	}

	public void setCriteriosPonderacionElegidos(List<Pair<Criterio, Double>> criteriosPuntajes) {
		this.criteriosPonderacionElegidos = criteriosPuntajes;
	}
	
	public List<Double> getPonderaciones() {
		return ponderaciones;
	}

	public void setPonderaciones(List<Double> ponderaciones) {
		this.ponderaciones = ponderaciones;
	}

	public Double getPonderacionSeleccionada() {
		return ponderacionSeleccionada;
	}

	public void setPonderacionSeleccionada(Double ponderacionSeleccionada) {
		this.ponderacionSeleccionada = ponderacionSeleccionada;
	}

	public void actualizarListaCriterios() {
		this.criterios = new LinkedList<>(RepositorioCriterios.getInstance().getCriterios());
		ObservableUtils.firePropertyChanged(this, "criterios");
	}
	
	public void borrarUltimoCriterio() {
		try{
			this.criteriosPonderacionElegidos.remove(criteriosPonderacionElegidos.size() - 1);
			ObservableUtils.firePropertyChanged(this, "criteriosPonderacionElegidos");
		}catch(IndexOutOfBoundsException exception){
			
		}
	}
	
	private void discriminarCriterio() {
		if(this.criterioSeleccionadoEsTaxativo())
			criteriosPonderacionElegidos.add(Pair.with(criterioSeleccionado, -1.0));
		else
			this.controlarValidezCriterioComparativo();
	}
	
	public Boolean criterioSeleccionadoEsTaxativo() {
		return this.criterioSeleccionado.getClass().equals(CriterioTaxativo.class);
	}
	
	private void controlarValidezCriterioComparativo() {
		if(ponderacionSeleccionada != null)
			criteriosPonderacionElegidos.add(Pair.with(criterioSeleccionado, ponderacionSeleccionada));
	}
}
