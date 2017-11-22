package indicators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.parser.objetosParser.IndicadorParser;

@Entity
public class IndicadorConResultado{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String empresa;
	private String periodo;
	private String valor;
	
	public IndicadorConResultado() {
	}
	
	public IndicadorConResultado(String nombre, String empresa, String periodo) {
		this.nombre=nombre;
		this.empresa=empresa;
		this.periodo=periodo;
		
	}
	
	//////////////////////////////////////////////////////
	////////////GETTERS AND SETTERS///////////////////////
	//////////////////////////////////////////////////////
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getEmpresa() {
		return this.empresa;
	}
	
	public void setEmpresa(String unaEmpresa) {
		this.empresa = unaEmpresa;
	}
	
	public void setPeriodo(String unPeriodo) {
		this.periodo = unPeriodo;
	}
	
	public String getPeriodo() {
		return this.periodo;
	}
	
	//////////////////////////////////
	//////////////////////////////////
	
	public void obtenerResultado(String operacion) {
		try {
			double resultado = new IndicadorParser(this.nombre, operacion).operar(this.empresa, this.periodo);
			this.setValor(String.valueOf(resultado));
		} catch (Exception e) {
			this.setValor("No se pudo calcular");
		}
	}
}