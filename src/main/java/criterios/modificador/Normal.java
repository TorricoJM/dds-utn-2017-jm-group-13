package criterios.modificador;

import indicators.Indicador;
import model.Empresa;

public class Normal extends Modificador{
	
	public String periodo;
	
	public Normal(String periodo){
		this.periodo = periodo;
	}
	
	@Override
	public double modificar(Empresa empresa,Indicador indicador){
		return indicador.evaluateEn(empresa.getNombre(), this.periodo);
	}
}