package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import criterios.Criterio;
import methodologies.MetodologiesBuilder;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioMetodologias;

@Observable
public class CrearMetodologiaViewModel {

	private List<Criterio> criterios;
	private Criterio criterioSeleccionado;
	private String cuentaSeleccionada;
	private String nombre;
	private List<Pair<Criterio,Double>> criteriosPuntajesElegidos = new LinkedList<>();

	public CrearMetodologiaViewModel() {
		this.criterios = RepositorioCriterios.getInstance().getCriterios();
	}

	public void agregarCriterio() {
		if (criterioSeleccionado == null) {
			throw new Exception("Seleccione un criterio.");
		} else {
			criteriosPuntajesElegidos.add(Pair.with(criterioSeleccionado, 1.0)); //Harcodeado puntaje por ahora
			ObservableUtils.firePropertyChanged(this, "criteriosPuntajesElegidos");
		}
	}

	public void crearMetodologia() {
		if (criteriosPuntajesElegidos.size() == 0) {
			throw new Exception("Agregue criterios");
		} else {
			RepositorioMetodologias.getInstance()
					.agregar(new MetodologiesBuilder().setNombre(nombre).setCriteriosPuntajes(criteriosPuntajesElegidos).build());
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
	
	public List<Criterio> getCriteriosPuntajesElegidos() {
		return criteriosPuntajesElegidos.stream().map(pair -> pair.getValue0()).collect(Collectors.toList());
	}

	public void setCriteriosPuntajesElegidos(List<Pair<Criterio, Double>> criteriosPuntajes) {
		this.criteriosPuntajesElegidos = criteriosPuntajes;
	}

	public void actualizarListaCriterios() {
		this.criterios = new LinkedList<>(RepositorioCriterios.getInstance().getCriterios());
		ObservableUtils.firePropertyChanged(this, "criterios");
	}

}
