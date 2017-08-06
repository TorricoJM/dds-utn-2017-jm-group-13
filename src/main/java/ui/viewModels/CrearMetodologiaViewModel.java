package ui.viewModels;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
//import org.uqbar.arena.widgets.List;
import org.uqbar.commons.utils.Observable;

import criterios.Criterio;
import methodologies.Metodologia;
import methodologies.MetodologiesBuilder;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioMetodologias;

@Observable
public class CrearMetodologiaViewModel {

	private List<Criterio> criterios;
	private Criterio criterioSeleccionado;
	private String cuentaSeleccionada;
	private List<Criterio> criteriosElegidos = new LinkedList<>();
	private String nombre;

	public CrearMetodologiaViewModel() {
		this.criterios = RepositorioCriterios.getInstance().getCriterios();
	}

	public void agregarCriterio() {
		if (criterioSeleccionado == null) {
			throw new Exception("Seleccione un criterio.");
		} else {
			criteriosElegidos.add(criterioSeleccionado);
			ObservableUtils.firePropertyChanged(this, "criteriosElegidos");
		}
	}

	public void crearMetodologia() {
		if (criteriosElegidos.size() == 0) {
			throw new Exception("Agregue criterios");
		} else {
			RepositorioMetodologias.getInstance()
					.agregar(new MetodologiesBuilder().setNombre(nombre).setCriterios(criterios).build());
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

	public List<Criterio> getCriteriosElegidos() {
		return criteriosElegidos;
	}

	public void setCriteriosElegidos(List<Criterio> criteriosElegidos) {
		this.criteriosElegidos = criteriosElegidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
