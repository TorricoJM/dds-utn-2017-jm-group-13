package user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class User {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String password;
	
	public User() {
	}
	
	public User(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
