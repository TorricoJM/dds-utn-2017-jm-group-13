package criterios.modificador;

import indicators.Indicador;
import model.Empresa;

public abstract class Modificador{

	public abstract double modificar(Empresa empresa, Indicador indicador);

}