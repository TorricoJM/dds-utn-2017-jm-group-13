package methodologies;

import java.util.LinkedList;
import java.util.List;

import criterios.*;
import criterios.modificador.Normal;
import indicators.PredefinidoROA;

public class MetodologiesBuilder {

	private Metodologia metodologia;

	public MetodologiesBuilder() {
		this.metodologia = new Metodologia("", null, null);
	}

	public MetodologiesBuilder setNombre(String nombre) {
		this.metodologia.setNombre(nombre);

		return this;
	}

	public MetodologiesBuilder setCriterios(
			List<ParComparativoPeso> criteriosComparativosPonderacion,
			List<CriterioTaxativo> criteriosTaxativos) {

		this.metodologia.setCriteriosComparativosPonderacion(criteriosComparativosPonderacion);
		this.metodologia.setCriteriosTaxativos(criteriosTaxativos);

		return this;
	}

	public MetodologiesBuilder buildPredefWarrenBuffet() {
		this.metodologia.setNombre("Warren Buffet");

		List<ParComparativoPeso> criteriosComparativos = new LinkedList<>();
		List<CriterioTaxativo> criteriosTaxativos = new LinkedList<>();

		criteriosTaxativos.add(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR,
				new PredefinidoROA(), new Normal(), 1));
		criteriosComparativos.add(new ParComparativoPeso(
				new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, new PredefinidoROA()),
				1.0));
		criteriosComparativos.add(new ParComparativoPeso(new CriterioComparativo("Minimizar el nivel de deuda",
				OperadorComparacion.MENOR, new PredefinidoROA()), 1.0));

		this.metodologia.setCriteriosComparativosPonderacion(criteriosComparativos);
		this.metodologia.setCriteriosTaxativos(criteriosTaxativos);

		return this;
	}

	public Metodologia build() {
		return this.metodologia;
	}
}
