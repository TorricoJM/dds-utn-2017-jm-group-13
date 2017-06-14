package indicators;

public class PredefinidoROI extends DataIndicador{

	private static PredefinidoROI instance;
	
	private PredefinidoROI(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}
	
	public static PredefinidoROI getInstance() {
		if (instance == null) {
			instance = new PredefinidoROI("ROI", "Ingresos - Inversión)/Inversión*100");
		}
		return instance;
	}

}
