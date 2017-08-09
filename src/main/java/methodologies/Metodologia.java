package methodologies;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import criterios.Criterio;
import model.Empresa;

import org.javatuples.*;
import org.uqbar.commons.utils.Observable;

@Observable
public class Metodologia {
	private String nombre;
	private List<Pair<Criterio, Double>> criteriosPonderacion;

	public Metodologia(String nombre, List<Pair<Criterio, Double>> criteriosPuntajes) {
		this.setNombre(nombre);
		this.setCriteriosPuntajes(criteriosPuntajes);
	}

	public List<Empresa> aplicarMetodologiaA(List<Empresa> listaEmpresas, List<String> listaPeriodos) {

		List<Empresa> empresasResultantes = this.aplicarCriteriosTaxativos(listaEmpresas, listaPeriodos);

		return this.aplicarCriteriosComparativos(empresasResultantes, listaPeriodos);
	}

	private List<Empresa> aplicarCriteriosComparativos(List<Empresa> empresas, List<String> periodos) {

		return this.establecerPuntajesTotales(empresas, periodos).stream()
				.sorted((e1, e2) -> Double.compare(e1.getValue1(), e2.getValue1())).map(tupla -> tupla.getValue0())
				.collect(Collectors.toList());
	}

	private List<Empresa> aplicarCriteriosTaxativos(List<Empresa> listaEmpresas, List<String> periodos) {

		List<Empresa> empresasResultantes = new LinkedList<>(listaEmpresas);
		
		return empresasResultantes.stream().filter(empresa -> this.cumpleTodosLosTaxativosUna(empresa, periodos))
				.collect(Collectors.toList());
	}

	private boolean cumpleTodosLosTaxativosUna(Empresa empresa, List<String> periodos) {
		return this.obtenerCriteriosTaxativos().stream()
				.allMatch(unTaxativo -> unTaxativo.verificarParaUna(empresa, periodos));
	}

	private List<Criterio> obtenerCriteriosTaxativos() {
		return this.criteriosPonderacion.stream().filter((tupla) -> tupla.getValue1() < 0.0)
				.map((tupla) -> tupla.getValue0()).collect(Collectors.toList());
	}

	private List<Pair<Criterio, Double>> obtenerCriteriosComparativosConPonderacion() {
		return this.criteriosPonderacion.stream().filter((tupla) -> tupla.getValue1() > 0.0)
				.collect(Collectors.toList());
	}

	private List<Pair<Empresa, Double>> establecerPuntajesTotales(List<Empresa> empresas, List<String> periodos) {

		return empresas.stream().map(empresa -> this.puntajeTotalDe(empresa, empresas, periodos))
				.collect(Collectors.toList());
	}

	private Pair<Empresa, Double> puntajeTotalDe(Empresa empresa, List<Empresa> empresas, List<String> periodos) {

		return Pair.with(empresa,
				this.obtenerCriteriosComparativosConPonderacion().stream()
						.map(criterio -> puntajeParcialDe(empresa, empresas, periodos, criterio))
						.mapToDouble(valor -> new Double(valor)).sum());
	}

	private double puntajeParcialDe(Empresa empresa, List<Empresa> empresas, List<String> periodos,
			Pair<Criterio, Double> criterio) {

		return criterio.getValue0().posicionLuegoDeAplicarDe(empresa, empresas, periodos) * criterio.getValue1();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pair<Criterio, Double>> getCriteriosPuntajes() {
		return criteriosPonderacion;
	}

	public void setCriteriosPuntajes(List<Pair<Criterio, Double>> criteriosPuntajes) {
		this.criteriosPonderacion = criteriosPuntajes;
	}
}
