package model.parser;

@SuppressWarnings("serial")
public class ErrorEvaluacionException extends RuntimeException{
	
	private String mensaje;
	
	public ErrorEvaluacionException(String string){
		this.mensaje = string;
	}
	
	public String getMensaje(){
		return "ERROR: " + this.mensaje;
	}
}