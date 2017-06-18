package model.parser;


@SuppressWarnings("serial")
public class ErrorEmpresaPeriodoVacioException extends RuntimeException{
	private String mensaje;
	
	public ErrorEmpresaPeriodoVacioException(String mensaje){
		this.mensaje = mensaje;
	}
	
	public String getMensaje(){
		return this.mensaje;
	}
}