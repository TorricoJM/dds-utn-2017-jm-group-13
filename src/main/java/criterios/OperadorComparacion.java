package criterios;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum OperadorComparacion {
	
	MAYOR{
		@Override
		public Boolean aplicar(double operando1, double operando2){
			return operando1 > operando2;
		}
		
		@Override
		public Boolean verificar(List<Double> valores) {
			List<Double> duplicada = this.duplicarLista(valores);
			Collections.reverse(duplicada);
			return valores.equals(duplicada);
		}

	},

	MENOR{
		@Override
		public Boolean aplicar(double operando1, double operando2){
			return operando1 < operando2;
		}
		
		@Override
		public Boolean verificar(List<Double> valores) {
			List<Double> duplicada = this.duplicarLista(valores);
			Collections.sort(duplicada);
			return valores.equals(duplicada);
		}
	};
	
	public abstract Boolean aplicar(double operando1, double operando2);

	public abstract Boolean verificar(List<Double> valores);
	
	public List<Double> duplicarLista(List<Double> valores){
		return valores.stream().collect(Collectors.toList());
	}
}