package criterios;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import criterios.modificador.Modificador;
import indicators.Indicador;
import model.Empresa;

@Observable
public class CriterioTaxativo implements Criterio {

	public String nombre;
	public List<Empresa> empresas;
	public Indicador indicador;
	public Modificador modificador;
	public OperadorComparacion operador;
	public double valor;

	public CriterioTaxativo(String nombre, OperadorComparacion operador, Indicador indicador, Modificador modificador,
			double valor) {
		this.nombre = nombre;
		this.operador = operador;
		this.indicador = indicador;
		this.modificador = modificador;
		this.valor = valor;
	}

	@Override
	public boolean verificarParaUna(Empresa empresa, List<String> periodos) {
		return modificador.modificar(empresa, indicador, periodos).stream()
				.allMatch((valorObtenido) -> operador.aplicar(valorObtenido, valor));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double posicionLuegoDeAplicarDe(Empresa empresa, List<Empresa> empresas, List<String> periodos) {
		return null;
	}

}