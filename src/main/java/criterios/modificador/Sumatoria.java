package criterios.modificador;

import java.util.List;
import java.util.stream.DoubleStream;

import indicators.Indicador;
import model.Empresa;

public class Sumatoria extends Modificador {

	@Override
	public List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos) {

		valoresResultantes.add(this.sumarValores(empresa, indicador, listaPeriodos));

		return valoresResultantes;

		// return operador.aplicar(this.sumarValores(empresa, indicador, lista,
		// operador, valor), valor);

	}

	public Double sumarValores(Empresa empresa, Indicador indicador, List<String> listaPeriodos) {

		DoubleStream valores = listaPeriodos.stream()
				.mapToDouble(periodo -> indicador.evaluateEn(empresa.getNombre(), periodo));

		return valores.sum();

	}

}