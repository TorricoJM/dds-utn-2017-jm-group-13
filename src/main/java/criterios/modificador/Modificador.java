package criterios.modificador;


import java.util.LinkedList;
import java.util.List;

import indicators.Indicador;
import model.Empresa;

public abstract class Modificador{
	
	List<Double> valoresResultantes = new LinkedList<>();

	public abstract List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos);

}