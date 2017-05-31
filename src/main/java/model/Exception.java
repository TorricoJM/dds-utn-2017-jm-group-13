package model;

@SuppressWarnings("serial")
public class Exception extends RuntimeException {
	
	private String mensaje;
	
	public Exception(String cadena){
		this.mensaje = cadena;
	}
	
	public String getMensaje(){
		return "ERROR: " + this.mensaje;
	}
}