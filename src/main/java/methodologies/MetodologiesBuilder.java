package methodologies;

import java.util.LinkedList;
import java.util.List;

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
	
	public MetodologiesBuilder setCriterios(List<Criterio> criterios) {
		this.metodologia.setCriterios(criterios);
		
		return this;
	}
	
	public MetodologiesBuilder buildPredefWarrenBuffet() {
		this.metodologia.setNombre("Warren Buffet");
		
		List<Criterio> criterios = new LinkedList<>();
		
		criterios.add(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1));
		criterios.add(new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()));
		criterios.add(new CriterioComparativo("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance()));
		
		this.metodologia.setCriterios(criterios);
		
		return this;
	}
	
	public Metodologia build() {
		return this.metodologia;
	}
}
