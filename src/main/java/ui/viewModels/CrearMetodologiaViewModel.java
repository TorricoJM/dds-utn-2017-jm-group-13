package ui.viewModels;


//import org.uqbar.arena.widgets.List;
import org.uqbar.commons.utils.Observable;

import model.Criterio;
import model.Exception;

@Observable
public class CrearMetodologiaViewModel {
	
	//private List<Criterio> criterios;
	private Criterio criterioSeleccionado;
	private String cuentaSeleccionada;
	private String metodologia = "";

	public void agregarCriterio() {
		if (criterioSeleccionado == null) {
			throw new Exception("Seleccione un criterio.");
		} else {
			this.setMetodologia(metodologia + criterioSeleccionado.getNombreCriterio());
		}
	}

	public String getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

}
