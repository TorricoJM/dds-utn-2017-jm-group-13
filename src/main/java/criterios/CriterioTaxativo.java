package criterios;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import criterios.modificador.Modificador;
import indicators.Indicador;
import model.Empresa;
import repositories.RepositorioEmpresas;

@Observable
public class CriterioTaxativo implements Criterio{
	
	public String nombre;
	public List<Empresa> empresas;
	public Indicador indicador;
	public Modificador modificador;
	public OperadorComparacion operador;
	public double valor;
	
	public CriterioTaxativo(String nombre, OperadorComparacion operador, Indicador indicador, Modificador modificador, double valor){
		this.nombre = nombre;
		this.operador = operador;
		this.indicador = indicador;
		this.modificador = modificador;
		this.valor = valor;
		this.empresas = RepositorioEmpresas.getInstance().getListaEmpresas();
	}

	@Override
	public List<Empresa> evaluar() {
				
		return empresas.stream().filter(empresa -> operador.aplicar(modificador.modificar(empresa, indicador), this.valor)).collect(Collectors.toList());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}