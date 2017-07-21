package criterios.modificador;

import java.util.List;

import criterios.OperadorComparacion;
import indicators.Indicador;
import model.Empresa;

public class Promedio extends Modificador{
	
	@Override
	public Boolean modificar(Empresa empresa, Indicador indicador, List<String> lista, OperadorComparacion operador, Double valor){
		
		return operador.aplicar(new Sumatoria().sumarValores(empresa, indicador, lista, operador, valor)/lista.size(), valor);
	}
}