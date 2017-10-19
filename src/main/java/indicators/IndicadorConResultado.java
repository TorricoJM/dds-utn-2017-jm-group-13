package indicators;

public class IndicadorConResultado{
	public String nombre;
	public double valor;
	public IndicadorConResultado(String nombre, double resultado) {
		this.nombre=nombre;
		this.valor=resultado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}