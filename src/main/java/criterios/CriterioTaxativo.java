package criterios;

import criterios.modificador.Modificador;
import indicators.Indicador;
import model.Empresa;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class CriterioTaxativo implements Criterio{
	
	public String empresa;
	public String indicador;
	public Modificador modificador;
	public OperadorComparacion operador;
	public double valor;
	
	public CriterioTaxativo(OperadorComparacion operador, String empresa, String indicador, Modificador modificador, double valor){
		this.operador = operador;
		this.empresa = empresa;
		this.indicador = indicador;
		this.modificador = modificador;
		this.valor = valor;
	}

	@Override
	public Boolean evaluar() {
		Empresa empresa = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre(this.empresa);
		Indicador indicador = RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre(this.indicador);
		
		return operador.aplicar(modificador.modificar(empresa,indicador), this.valor);
	}

}