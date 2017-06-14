package indicators;

public class PredefinidoROA extends DataIndicador {

	private static PredefinidoROA instance;
	
	private PredefinidoROA(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}

	public static PredefinidoROA getInstance() {
		if (instance == null) {
			instance = new PredefinidoROA("ROA", "Utilidad Neta/Activo Total");
		}
		return instance;
	}
}
