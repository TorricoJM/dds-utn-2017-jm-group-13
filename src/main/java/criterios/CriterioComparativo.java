package criterios;

import indicators.Indicador;
import repositories.RepositorioIndicadores;

public class CriterioComparativo implements Criterio{
	
	public String empresa1;
	public String empresa2;
	public String indicador;
	public OperadorComparacion operador;
	public String periodo;
	
	public CriterioComparativo(OperadorComparacion operador, String empresa1, String empresa2, String indicador, String periodo){
		this.operador = operador;
		this.empresa1 = empresa1;
		this.empresa2 = empresa2;
		this.indicador = indicador;
		this.periodo = periodo;
	}
	
	public Boolean evaluar(){
		Indicador indicador = RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre(this.indicador);
		
		return operador.aplicar(indicador.evaluateEn(this.empresa1, this.periodo), indicador.evaluateEn(this.empresa2, this.periodo));
	}
}