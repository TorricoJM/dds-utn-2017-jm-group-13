package criterios.modificador;

import java.util.List;

import criterios.OperadorComparacion;
import indicators.Indicador;
import model.Empresa;
import model.PeriodoFiscal;

public class Promedio extends Modificador{
	
	@Override
	public Boolean modificar(Empresa empresa, Indicador indicador, List<PeriodoFiscal> lista, OperadorComparacion operador, Double valor){
		
		return operador.aplicar(new Sumatoria().sumarValores(empresa, indicador, lista, operador, valor)/lista.size(), valor);
	}
}