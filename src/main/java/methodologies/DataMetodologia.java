package methodologies;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import criterios.Criterio;
import model.Empresa;

import org.apache.commons.lang.StringUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class DataMetodologia {
	private String nombre;
	private List<Criterio> criterios;
	
	public DataMetodologia(String nombre, List<Criterio> criterios){
		this.setNombre(nombre);
		this.setCriterios(criterios);
	}
	
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, List<String> listaPeriodos) {		
		List<Empresa> empresasResultantes = listaEmpresas;
		
		/*
		for (int i = 0; i < this.criterios.size(); i++){
		empresasResultantes.addAll(this.criterios.get(i).evaluar(listaPeriodos, listaEmpresas));
		}
		
		return empresasResultantes.stream().filter(e -> this.cuantasVecesSeRepiteLaEmpresa(empresasResultantes, e) == this.criterios.size()).distinct().collect(Collectors.toList());
	*/
		criterios.stream().forEach(criterio -> this.evaluarCriterioEn(criterio,empresasResultantes,listaPeriodos));
	
		return empresasResultantes;
	}
	
	private void evaluarCriterioEn(Criterio criterio,List<Empresa> empresasResultantes,List<String> listaPeriodos) {
		empresasResultantes = criterio.evaluar(listaPeriodos, empresasResultantes);
	}
	
	public int cuantasVecesSeRepiteLaEmpresa(List<Empresa> listaEmpresas, Empresa empresa){
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
