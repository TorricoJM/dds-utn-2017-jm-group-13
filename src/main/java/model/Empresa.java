package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {

	private String id;
	private String razonSocial;
	private String nombreFantasia;
	private String cuit; 

	//private List<Grade> grades = new LinkedList<>();

	public Empresa() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/*public List<Grade> getGrades() {
		return grades;
	}
	
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}*/

	
	

}