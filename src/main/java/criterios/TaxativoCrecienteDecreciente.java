package criterios;

import java.util.List;

import criterios.modificador.Modificador;
import indicators.Indicador;
import model.Empresa;

public class TaxativoCrecienteDecreciente extends CriterioTaxativo{

	public TaxativoCrecienteDecreciente(String nombre, OperadorComparacion operador, Indicador indicador,
			Modificador modificador, double valor) {
		super(nombre, operador, indicador, modificador, valor);
	}
	
	public Boolean verificarParaUna(Empresa empresa, List<String> periodos) {
		return this.operador.verificar(modificador.modificar(empresa, this.indicador, periodos));
	}
	
}