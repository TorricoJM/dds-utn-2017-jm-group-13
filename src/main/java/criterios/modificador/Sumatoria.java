package criterios.modificador;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.DoubleStream;

import javax.persistence.Entity;

import indicators.Indicador;
import model.Empresa;

@Entity
public class Sumatoria extends Modificador {

	@Override
	public List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos) {

		List<Double> valoresResultantes = new LinkedList<>();
		
		valoresResultantes.add(this.sumarValores(empresa, indicador, listaPeriodos));

		return valoresResultantes;

	}

	public Double sumarValores(Empresa empresa, Indicador indicador, List<String> listaPeriodos) {

		DoubleStream valores = listaPeriodos.stream()
				.mapToDouble(periodo -> indicador.evaluateEn(empresa.getNombre(), periodo));

		return valores.sum();

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