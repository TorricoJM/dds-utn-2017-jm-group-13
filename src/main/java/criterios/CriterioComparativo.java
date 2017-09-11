package criterios;

import java.util.List;
import java.util.stream.Collectors;

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
import criterios.modificador.Sumatoria;
import indicators.Indicador;
import model.Empresa;

@Entity
@Observable
public class CriterioComparativo implements Criterio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String nombre;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public Indicador indicador;
	
	@Enumerated(EnumType.STRING)
	public OperadorComparacion operador;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public Modificador modificador = new Sumatoria();
	
	

	public CriterioComparativo(String nombre, OperadorComparacion operador, Indicador indicador) {
		this.nombre = nombre;
		this.operador = operador;
		this.indicador = indicador;
	}
	
	public CriterioComparativo(){
	}

	@Override
	public Double posicionLuegoDeAplicarDe(Empresa empresa, List<Empresa> empresas, List<String> periodos) {
		return new Double(empresas.stream()
				.sorted((e1, e2) -> Boolean.compare(
						operador.aplicar(modificador.modificar(e2, indicador, periodos).get(0),
								modificador.modificar(e1, indicador, periodos).get(0)),
						operador.aplicar(modificador.modificar(e1, indicador, periodos).get(0),
								modificador.modificar(e2, indicador, periodos).get(0))))
				.collect(Collectors.toList()).indexOf(empresa) + 1);
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public OperadorComparacion getOperador() {
		return operador;
	}

	public void setOperador(OperadorComparacion operador) {
		this.operador = operador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean verificarParaUna(Empresa empresa, List<String> periodos) {
		return false;
	}

}