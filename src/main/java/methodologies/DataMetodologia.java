package methodologies;

import java.util.List;

import criterios.Criterio;

import org.uqbar.commons.utils.Observable;

@Observable
public class DataMetodologia {
	private String nombre;
	private List<Criterio> criterios;
	
	public DataMetodologia(String nombre, List<Criterio> criterios){
		this.setNombre(nombre);
		this.setCriterios(criterios);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}
}
