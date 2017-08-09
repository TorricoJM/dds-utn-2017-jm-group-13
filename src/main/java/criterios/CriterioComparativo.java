package criterios;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import criterios.modificador.Modificador;
import criterios.modificador.Sumatoria;
import indicators.Indicador;
import model.Empresa;

@Observable
public class CriterioComparativo implements Criterio {

	public String nombre;
	public Indicador indicador;
	public OperadorComparacion operador;
	public Modificador modificador = new Sumatoria();

	public CriterioComparativo(String nombre, OperadorComparacion operador, Indicador indicador) {
		this.nombre = nombre;
		this.operador = operador;
		this.indicador = indicador;
	}

	public List<Empresa> evaluar(List<String> listaPeriodos, List<Empresa> empresas) {

		List<Empresa> orden = empresas.stream()
				.sorted((e1, e2) -> Double.compare(modificador.modificar(e2, indicador, listaPeriodos).get(0),
						modificador.modificar(e1, indicador, listaPeriodos).get(0)))
				.collect(Collectors.toList());

		return orden;
	}

	@Override
	public Double posicionLuegoDeAplicarDe(Empresa empresa, List<Empresa> empresas, List<String> periodos) {
		return new Double(empresas.stream()
				.sorted((e1, e2) -> Double.compare(modificador.modificar(e2, indicador, periodos).get(0),
						modificador.modificar(e1, indicador, periodos).get(0)))
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

	public boolean verificarParaUna(Empresa empresa, List<String> periodos) {
		return false;
	}

}