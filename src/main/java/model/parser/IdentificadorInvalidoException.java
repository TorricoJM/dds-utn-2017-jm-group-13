package model.parser;

@SuppressWarnings("serial")
public class IdentificadorInvalidoException extends RuntimeException{
	public String mensaje;
	
	public IdentificadorInvalidoException(String mensaje){
		this.mensaje = mensaje;
	}
	
	public String getMensaje(){
		return this.mensaje;
	}
}