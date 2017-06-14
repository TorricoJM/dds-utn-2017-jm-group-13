package indicators;

public class PredefinidoROE extends DataIndicador{

	private static PredefinidoROE instance;
	
	private PredefinidoROE(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}
	
	public static PredefinidoROE getInstance() {
		if (instance == null) {
			instance = new PredefinidoROE("ROE", "Utilidad Neta/Patrimonio Total");
		}
		return instance;
	}
}
