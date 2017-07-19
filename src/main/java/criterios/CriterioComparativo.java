package criterios;

import java.util.Collections;
import java.util.List;

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
		
		// operador.aplicar(indicador.evaluateEn(this.empresa1, this.periodo), indicador.evaluateEn(this.empresa2, this.periodo));
		// Hay que filtrar la lista de empresas con el metodo que esta aca arriba
		
		return empresas;
	}
}