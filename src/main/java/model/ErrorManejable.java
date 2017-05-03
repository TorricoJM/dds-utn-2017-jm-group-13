package model;

@SuppressWarnings("serial")
public class ErrorManejable extends RuntimeException {
	public ErrorManejable(String cadena,Throwable ex){
		System.out.println(cadena + ex.getMessage());
	}
}