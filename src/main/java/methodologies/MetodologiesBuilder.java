package methodologies;

import java.util.LinkedList;
import java.util.List;

import org.javatuples.Pair;

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
			List<Pair<CriterioComparativo, Double>> criteriosComparativosPonderacion,
			List<CriterioTaxativo> criteriosTaxativos) {

		this.metodologia.setCriteriosComparativosPonderacion(criteriosComparativosPonderacion);
		this.metodologia.setCriteriosTaxativos(criteriosTaxativos);

		return this;
	}

	public MetodologiesBuilder buildPredefWarrenBuffet() {
		this.metodologia.setNombre("Warren Buffet");

		List<Pair<CriterioComparativo, Double>> criteriosComparativos = new LinkedList<>();
		List<CriterioTaxativo> criteriosTaxativos = new LinkedList<>();

		criteriosTaxativos.add(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR,
				PredefinidoROA.getInstance(), new Normal(), 1));
		criteriosComparativos.add(Pair.with(
				new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()),
				1.0));
		criteriosComparativos.add(Pair.with(new CriterioComparativo("Minimizar el nivel de deuda",
				OperadorComparacion.MENOR, PredefinidoROA.getInstance()), 1.0));

		this.metodologia.setCriteriosComparativosPonderacion(criteriosComparativos);
		this.metodologia.setCriteriosTaxativos(criteriosTaxativos);

		return this;
	}

	public Metodologia build() {
		return this.metodologia;
	}
}
