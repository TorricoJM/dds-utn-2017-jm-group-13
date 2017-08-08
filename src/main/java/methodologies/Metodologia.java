package methodologies;

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
	private List<Pair<Criterio,Double>> criteriosPuntajes;
	
	public Metodologia(String nombre, List<Pair<Criterio,Double>> criteriosPuntajes) {
		this.setNombre(nombre);
		this.setCriteriosPuntajes(criteriosPuntajes);
	}

	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, List<String> listaPeriodos) {

		List<Criterio> criterios = criteriosPuntajes.stream().map(pair -> pair.getValue0()).collect(Collectors.toList());
		
		return this.reduccionFea(criterios,listaEmpresas,listaPeriodos);
	}

	private List<Empresa> reduccionFea(List<Criterio> criterios, List<Empresa> listaEmpresas,
			List<String> listaPeriodos) {
		
		List<Empresa> empresasResultantes = listaEmpresas;
		
		for(int i = 0; i < criterios.size(); i++) {
			empresasResultantes = criterios.get(i).evaluar(listaPeriodos, empresasResultantes);
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
		return criteriosPuntajes;
	}

	public void setCriteriosPuntajes(List<Pair<Criterio, Double>> criteriosPuntajes) {
		this.criteriosPuntajes = criteriosPuntajes;
	}


}
