package model;

public class Indicador {

	private String nombre;
	private String operacion;

	public Indicador(String nombre, String operacion) {
		this.nombre = nombre;
		this.operacion = operacion;
	}

	// -----------------------------------------------------GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	// ---------------------------------------------------/GETTERS AND SETTERS

	public void ejecutarOperacion() {
		// TODO para manu
	}
}
