package criterios;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.uqbar.commons.utils.Observable;

import criterios.modificador.Modificador;
import indicators.Indicador;
import model.Empresa;

@Entity
@Observable
public class CriterioTaxativo implements Criterio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String nombre;
		
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public Indicador indicador;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public Modificador modificador;
	
	@Enumerated(EnumType.STRING)
	public OperadorComparacion operador;

	public double valor;

	public CriterioTaxativo(){
	}
	
	public CriterioTaxativo(String nombre, OperadorComparacion operador, Indicador indicador, Modificador modificador,
			double valor) {
		this.nombre = nombre;
		this.operador = operador;
		this.indicador = indicador;
		this.modificador = modificador;
		this.valor = valor;
	}

	@Override
	public Boolean verificarParaUna(Empresa empresa, List<String> periodos) {
		return modificador.modificar(empresa, indicador, periodos).stream()
				.allMatch((valorObtenido) -> operador.aplicar(valorObtenido, valor));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public Modificador getModificador() {
		return modificador;
	}

	public void setModificador(Modificador modificador) {
		this.modificador = modificador;
	}

	public OperadorComparacion getOperador() {
		return operador;
	}

	public void setOperador(OperadorComparacion operador) {
		this.operador = operador;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Double posicionLuegoDeAplicarDe(Empresa empresa, List<Empresa> empresas, List<String> periodos) {
		return null;
	}

}