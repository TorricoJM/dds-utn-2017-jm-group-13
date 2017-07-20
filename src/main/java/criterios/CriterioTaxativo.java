package criterios;

import java.util.List;
import java.util.stream.Collectors;

import criterios.modificador.Modificador;
import indicators.Indicador;
import model.Empresa;
import repositories.RepositorioIndicadores;

public class CriterioTaxativo implements Criterio{
	
	public String nombre;
	public List<Empresa> empresas;
	public String indicador;
	public Modificador modificador;
	public OperadorComparacion operador;
	public double valor;
	
	public CriterioTaxativo(String nombre, OperadorComparacion operador, List<Empresa> empresas, String indicador, Modificador modificador, double valor){
		this.nombre = nombre;
		this.operador = operador;
		this.empresas = empresas;
		this.indicador = indicador;
		this.modificador = modificador;
		this.valor = valor;
	}

	@Override
	public List<Empresa> evaluar() {
		Indicador indicador = RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre(this.indicador);
		
		return empresas.stream().filter(empresa -> operador.aplicar(modificador.modificar(empresa, indicador), this.valor)).collect(Collectors.toList());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}