package model.parser.objetosParser;

public enum Operador {
	
	SUMA{
		@Override
		public double aplicar(double operando1, double operando2) {
			return operando1 + operando2;
		}
	},
	
	RESTA{
		@Override
		public double aplicar(double operando1, double operando2) {
			return operando1 - operando2;
		}
	},
	
	MULTIPLICACION{
		@Override
		public double aplicar(double operando1, double operando2){
			return operando1 * operando2;
		}
	},
	
	DIVISION{
		@Override
		public double aplicar(double operando1, double operando2){
			return operando1 / operando2;
		}
	};
	
	public abstract double aplicar(double operando1, double operando2);
	
}