package methodologies;

import java.util.LinkedList;
import java.util.List;

import org.javatuples.Pair;

import criterios.*;
import criterios.OperadorComparacion;
import criterios.modificador.Normal;
import indicators.PredefinidoROA;

public class MetodologiesBuilder {

	private Metodologia metodologia;
	
	public MetodologiesBuilder() {
		this.metodologia = new Metodologia("",null);
	}
	
	public MetodologiesBuilder setNombre(String nombre) {
		this.metodologia.setNombre(nombre);
		
		return this;
	}
	
	public MetodologiesBuilder setCriteriosPonderacion(List<Pair<Criterio,Double>> criteriosPuntajes) {
		this.metodologia.setCriteriosPuntajes(criteriosPuntajes);
		
		return this;
	}
	
	public MetodologiesBuilder buildPredefWarrenBuffet() {
		this.metodologia.setNombre("Warren Buffet");
		
		List<Pair<Criterio,Double>> criteriosPuntajes = new LinkedList<>();
		
		criteriosPuntajes.add(Pair.with(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1), 1.0));
		criteriosPuntajes.add(Pair.with(new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()), 1.0));
		criteriosPuntajes.add(Pair.with(new CriterioComparativo("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance()), 1.0));
		
		this.metodologia.setCriteriosPuntajes(criteriosPuntajes);
		
		return this;
	}
	
	public Metodologia build() {
		return this.metodologia;
	}
}
