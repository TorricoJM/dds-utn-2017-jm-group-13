package criterios;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import indicators.Indicador;
import model.Empresa;
import repositories.RepositorioIndicadores;

public class CriterioComparativo implements Criterio{
	
	public List<Empresa> empresas;
	public String indicador;
	public OperadorComparacion operador;
	public String periodo;
	
	public CriterioComparativo(OperadorComparacion operador, List<Empresa> empresas, String indicador, String periodo){
		this.operador = operador;
		this.empresas = empresas;
		this.indicador = indicador;
		this.periodo = periodo;
	}
	
	public List<Empresa> evaluar(){
		Indicador indicador = RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre(this.indicador);
		
		List<Empresa> orden = empresas.stream().sorted( (e1, e2) -> Double.compare(indicador.evaluateEn(e1.getNombre(), this.periodo),(indicador.evaluateEn(e2.getNombre(), this.periodo)))).collect(Collectors.toList());
		
		return Lists.reverse(orden);
		
	}
}