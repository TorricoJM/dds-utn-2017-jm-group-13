package criterios.modificador;

import java.util.stream.DoubleStream;

import indicators.Indicador;
import model.Empresa;

public class Sumatoria extends Modificador{
	
	@Override
	public double modificar(Empresa empresa, Indicador indicador){
		DoubleStream valores = empresa.getPeriodos().stream()
				.mapToDouble(periodo -> indicador.evaluateEn(empresa.getNombre(), periodo.getPeriodo()));
		return valores.sum();
	}
	
}