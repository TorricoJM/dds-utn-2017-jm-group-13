package user;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.uqbar.commons.utils.Observable;

import indicators.Indicador;
import methodologies.Metodologia;

@Observable
@Entity
public class User {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String password;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private List<Indicador> indicadores = new LinkedList<>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private List<Metodologia> metodologias = new LinkedList<>();
	
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

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}
	
	public long getId() {
		return this.id;
	}
}
