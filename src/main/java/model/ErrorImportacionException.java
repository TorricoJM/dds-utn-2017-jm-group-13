package model;

@SuppressWarnings("serial")
public class ErrorImportacionException extends RuntimeException {
	
	private String mensaje;
	
	public ErrorImportacionException(String cadena){
		this.mensaje = cadena;
	}
	
	public String getMensaje(){
		return "ERROR: " + this.mensaje;
	}
}