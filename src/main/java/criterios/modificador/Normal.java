package criterios.modificador;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import indicators.Indicador;
import model.Empresa;

@Entity
public class Normal extends Modificador{
		
	@Override
	public List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos){

		return listaPeriodos.stream().map(periodo -> indicador.evaluateEn(empresa.getNombre(), periodo)).collect(Collectors.toList());
		
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}