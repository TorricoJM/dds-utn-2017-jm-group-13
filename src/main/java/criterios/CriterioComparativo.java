package criterios;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.google.common.collect.Lists;

import indicators.Indicador;
import model.Empresa;

@Observable
public class CriterioComparativo implements Criterio{
	
	public String nombre;
	public Indicador indicador;
	public OperadorComparacion operador;
	
	public CriterioComparativo(String nombre, OperadorComparacion operador, Indicador indicador){
		this.nombre = nombre;
		this.operador = operador;
		this.indicador = indicador;
	}
	
	public List<Empresa> evaluar(List<String> listaPeriodos, List<Empresa> empresas){	
		List<Empresa> orden = empresas.stream().sorted( (e1, e2) -> Double.compare(indicador.evaluateEn(e1.getNombre(), listaPeriodos.get(0)),(indicador.evaluateEn(e2.getNombre(), listaPeriodos.get(0))))).collect(Collectors.toList());
		return Lists.reverse(orden);
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
	
	
}