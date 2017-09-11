
package model;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class Cuenta {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String valor;

	public Cuenta(String cuenta, String valor) {
		this.nombre = cuenta;
		this.valor = valor;
	}
	
	public Cuenta() {
	}

	public String getNombre() {
		return nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setNombre(String cuenta) {
		this.nombre = cuenta;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}