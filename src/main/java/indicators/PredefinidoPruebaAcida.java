package indicators;

public class PredefinidoPruebaAcida extends DataIndicador{

	private static PredefinidoPruebaAcida instance;
	
	private PredefinidoPruebaAcida(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}
	
	public static PredefinidoPruebaAcida getInstance() {
		if (instance == null) {
			instance = new PredefinidoPruebaAcida("Prueba Acida", "(Activo corriente–Inventarios)/Pasivo corriente");
		}
		return instance;
	}

}
