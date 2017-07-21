package criterios.modificador;


import java.util.List;

import criterios.OperadorComparacion;
import indicators.Indicador;
import model.Empresa;

public abstract class Modificador{

	public abstract Boolean modificar(Empresa empresa, Indicador indicador, List<String> lista, OperadorComparacion operador, Double valor);

}