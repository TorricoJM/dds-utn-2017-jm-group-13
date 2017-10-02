package criterios.modificador;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

import indicators.Indicador;
import model.Empresa;

@Entity
public class Promedio extends Modificador {

	@Override
	public List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos) {

		List<Double> valoresResultantes = new LinkedList<>();

		valoresResultantes.add(new Sumatoria().sumarValores(empresa, indicador, listaPeriodos) / listaPeriodos.size());

		return valoresResultantes;

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