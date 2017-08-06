package methodologies;

import java.util.List;
import java.util.stream.Collectors;

import criterios.Criterio;
import model.Empresa;

import org.apache.commons.lang.StringUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class Metodologia {
	private String nombre;
	private List<Criterio> criterios;

	public Metodologia(String nombre, List<Criterio> criterios) {
		this.setNombre(nombre);
		this.setCriterios(criterios);
	}

	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, List<String> listaPeriodos) {

		return this.reduccionFea(criterios,listaEmpresas,listaPeriodos);
	}

	private List<Empresa> reduccionFea(List<Criterio> criterios, List<Empresa> listaEmpresas,
			List<String> listaPeriodos) {
		
		List<Empresa> empresasResultantes = listaEmpresas.stream().collect(Collectors.toList());
		
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

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}
}
