package criterios.modificador;

import java.util.List;

import criterios.OperadorComparacion;
import indicators.Indicador;
import model.Empresa;

public class Normal extends Modificador{
		
	@Override
	public Boolean modificar(Empresa empresa, Indicador indicador, List<String> lista, OperadorComparacion operador, Double valor){

		return lista.stream().allMatch(periodo -> operador.aplicar(indicador.evaluateEn(empresa.getNombre(), periodo), valor));
	
	}
}