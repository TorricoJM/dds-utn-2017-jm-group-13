package criterios.modificador;

import java.util.List;
import java.util.stream.Collectors;

import indicators.Indicador;
import model.Empresa;

public class Normal extends Modificador{
		
	@Override
	public List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos){

		return listaPeriodos.stream().map(periodo -> indicador.evaluateEn(empresa.getNombre(), periodo)).collect(Collectors.toList());
		
		//return lista.stream().allMatch(periodo -> operador.aplicar(indicador.evaluateEn(empresa.getNombre(), periodo), valor));
	
	}
}