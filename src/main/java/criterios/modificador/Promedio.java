package criterios.modificador;

import indicators.Indicador;
import model.Empresa;

public class Promedio extends Modificador{
	
	@Override
	public double modificar(Empresa empresa, Indicador indicador){
		return new Sumatoria().modificar(empresa, indicador)/empresa.getPeriodos().size();
	}
}