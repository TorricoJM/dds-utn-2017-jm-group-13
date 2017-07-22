package methodologies;

import java.util.LinkedList;
import java.util.List;

import criterios.Criterio;
import criterios.PredefinidoMargenesCrecientes;
import criterios.PredefinidoMaximizarROE;
import criterios.PredefinidoMinimizarNivelDeuda;

public class PredefinidaWarrenBuffet extends DataMetodologia {
	
	private PredefinidaWarrenBuffet(String nombre, List<Criterio> criterios) {
		super(nombre, criterios);
	}

	private static PredefinidaWarrenBuffet instance;
	
	public static PredefinidaWarrenBuffet getInstance() {
		if (instance == null) {
			List<Criterio> criterios = new LinkedList<>();
			instance = new PredefinidaWarrenBuffet("Warren Buffet", criterios);
			criterios.add(PredefinidoMinimizarNivelDeuda.getInstance());
			criterios.add(PredefinidoMaximizarROE.getInstance());
			criterios.add(PredefinidoMargenesCrecientes.getInstance());
		}
		return instance;
	}
	
}
