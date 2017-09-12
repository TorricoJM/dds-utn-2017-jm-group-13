package criterios;

public class CriterioComparativoConPeso {

	private CriterioComparativo criterio;
	private Double peso;
	
	public CriterioComparativoConPeso(CriterioComparativo unCriterio, Double unPeso) {
		this.criterio = unCriterio;
		this.peso = unPeso;
	}

	public CriterioComparativo getCriterio() {
		return criterio;
	}

	public Double getPeso() {
		return peso;
	}	
}