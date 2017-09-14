package repositories.repoArchivos;

import criterios.Criterio;

public class RepositorioCriterios extends RepoArchivos<Criterio> {

	private static RepositorioCriterios instance;

	public static RepositorioCriterios getInstance() {
		if (instance == null) {
			instance = new RepositorioCriterios();
			//instance.agregar(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1));
			//instance.agregar(new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()));
			//instance.agregar(new CriterioComparativo("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance()));
		}
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	public boolean tieneCriterio(String nombre) {
		return this.getElementos().stream()
				.anyMatch(criterio -> criterio.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}
}