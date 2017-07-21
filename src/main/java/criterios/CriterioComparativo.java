package criterios;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import indicators.Indicador;
import model.Empresa;
import model.PeriodoFiscal;
import repositories.RepositorioIndicadores;

public class CriterioComparativo implements Criterio{
	
	public String nombre;
	public List<Empresa> empresas;
	public String indicador;
	public OperadorComparacion operador;
	public String periodo;
	
	public CriterioComparativo(String nombre, OperadorComparacion operador, List<Empresa> empresas, String indicador, String periodo){
		this.nombre = nombre;
		this.operador = operador;
		this.empresas = empresas;
		this.indicador = indicador;
		this.periodo = periodo;
	}
	
	public List<Empresa> evaluar(List<PeriodoFiscal> listaPeriodos){
		Indicador indicador = RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre(this.indicador);
		
		List<Empresa> orden = empresas.stream().sorted( (e1, e2) -> Double.compare(indicador.evaluateEn(e1.getNombre(), this.periodo),(indicador.evaluateEn(e2.getNombre(), this.periodo)))).collect(Collectors.toList());
		
		return Lists.reverse(orden);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}