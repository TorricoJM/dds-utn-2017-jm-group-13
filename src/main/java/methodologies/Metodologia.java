package methodologies;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import criterios.Criterio;
import model.Empresa;

import org.javatuples.*;
import org.apache.commons.lang.StringUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class Metodologia {
	private String nombre;
	private List<Pair<Criterio, Double>> criteriosPonderacion;

	public Metodologia(String nombre, List<Pair<Criterio, Double>> criteriosPuntajes) {
		this.setNombre(nombre);
		this.setCriteriosPuntajes(criteriosPuntajes);
	}

	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, List<String> listaPeriodos) {

		List<Criterio> criteriosTaxativos = criteriosPonderacion.stream().filter((tupla) -> tupla.getValue1() < 0.0)
				.map((tupla) -> tupla.getValue0()).collect(Collectors.toList());

		List<Pair<Criterio, Double>> criteriosComparativosPonderacion = criteriosPonderacion.stream()
				.filter((tupla) -> tupla.getValue1() > 0.0).collect(Collectors.toList());

		List<Empresa> empresasResultantes = aplicarCriteriosTaxativosFeo(criteriosTaxativos, listaEmpresas,
				listaPeriodos);

		empresasResultantes = aplicarCriteriosComparativos(criteriosComparativosPonderacion, empresasResultantes,
				listaPeriodos);

		return empresasResultantes;

	}

	private List<Empresa> aplicarCriteriosComparativos(List<Pair<Criterio, Double>> criteriosComparativos,
			List<Empresa> empresasResultantes, List<String> listaPeriodos) {

		List<Pair<Empresa, Double>> empresaPuntaje = empresasResultantes.stream()
				.map(empresa -> Pair.with(empresa, 0.0)).collect(Collectors.toList());

		for (int i = 0; i < criteriosComparativos.size(); i++) {
			empresasResultantes = criteriosComparativos.get(i).getValue0().evaluar(listaPeriodos, empresasResultantes);

			Double ponderacion = criteriosComparativos.get(i).getValue1();

			List<Empresa> empresasCopia = new LinkedList<>(empresasResultantes);

			empresasResultantes.forEach(empresa -> this.obtenerTuplaDesdeEmpresa(empresaPuntaje, empresa)
					.setAt1(this.obtenerTuplaDesdeEmpresa(empresaPuntaje, empresa).getValue1()
							+ (empresasCopia.indexOf(empresa) + 1) * ponderacion));
		}

		System.out.println(empresaPuntaje.stream()
				.sorted((tupla1, tupla2) -> Double.compare(tupla1.getValue1(), tupla2.getValue1()))
				.map(tupla -> tupla.getValue1()).collect(Collectors.toList()));

		return empresaPuntaje.stream()
				.sorted((tupla1, tupla2) -> Double.compare(tupla1.getValue1(), tupla2.getValue1()))
				.map(tupla -> tupla.getValue0()).collect(Collectors.toList());
	}

	private Pair<Empresa, Double> obtenerTuplaDesdeEmpresa(List<Pair<Empresa, Double>> listaTuplas, Empresa empresa) {

		return listaTuplas.stream().filter(tupla -> tupla.contains(empresa)).findFirst().get();

	}

	private List<Empresa> aplicarCriteriosTaxativosFeo(List<Criterio> criteriosTaxativos, List<Empresa> listaEmpresas,
			List<String> listaPeriodos) {

		List<Empresa> empresasResultantes = listaEmpresas;

		for (int i = 0; i < criteriosTaxativos.size(); i++) {
			empresasResultantes = criteriosTaxativos.get(i).evaluar(listaPeriodos, empresasResultantes);
		}

		return empresasResultantes;
	}

	public int cuantasVecesSeRepiteLaEmpresa(List<Empresa> listaEmpresas, Empresa empresa) {
		return StringUtils.countMatches(listaEmpresas.toString(), empresa.toString());
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
