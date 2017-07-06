package criterios;

public enum OperadorComparacion {
	
	MAYOR{
		@Override
		public Boolean aplicar(double operando1, double operando2){
			return operando1 > operando2;
		}
	},
	
	MENOR{
		@Override
		public Boolean aplicar(double operando1, double operando2){
			return operando1 < operando2;
		}
	};
	
	public abstract Boolean aplicar(double operando1, double operando2);
}