package model;

public class Criterio {
	private String nombreCriterio;
	private String criterio;

	public Criterio(String nombreCriterio, String criterio) {
		this.nombreCriterio = nombreCriterio;
		this.criterio = criterio;
	}

	public String getNombreCriterio() {
		return nombreCriterio;
	}

	public void setNombreCriterio(String nombreCriterio) {
		this.nombreCriterio = nombreCriterio;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
}
