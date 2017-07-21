package criterios.modificador;

import java.util.List;
import java.util.stream.DoubleStream;

import criterios.OperadorComparacion;
import indicators.Indicador;
import model.Empresa;

public class Sumatoria extends Modificador{
	
	@Override
	public Boolean modificar(Empresa empresa, Indicador indicador, List<String> lista, OperadorComparacion operador, Double valor){
		
		return operador.aplicar(this.sumarValores(empresa, indicador, lista, operador, valor), valor);
		
	}
	
	
	public Double sumarValores(Empresa empresa, Indicador indicador, List<String> lista, OperadorComparacion operador, Double valor){
		
		DoubleStream valores = lista.stream()
				.mapToDouble(periodo -> indicador.evaluateEn(empresa.getNombre(), periodo));
		
		return valores.sum();
		
	}
	
}