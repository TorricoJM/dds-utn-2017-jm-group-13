package model.parser;


@SuppressWarnings("serial")
public class EmpresaPeriodoVacioException extends RuntimeException{
	private String mensaje;
	
	public EmpresaPeriodoVacioException(String mensaje){
		this.mensaje = mensaje;
	}
	
	public String getMensaje(){
		return this.mensaje;
	}
}