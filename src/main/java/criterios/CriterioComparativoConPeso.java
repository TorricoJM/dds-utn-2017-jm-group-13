package criterios;

import javax.persistence.*;
import criterios.CriterioComparativo;
import org.uqbar.commons.utils.Observable;

@Entity
@Observable
public class CriterioComparativoConPeso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
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