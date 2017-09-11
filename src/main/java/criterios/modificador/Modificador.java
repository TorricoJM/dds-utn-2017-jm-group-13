package criterios.modificador;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import indicators.Indicador;
import model.Empresa;

@Entity
public abstract class Modificador{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public abstract List<Double> modificar(Empresa empresa, Indicador indicador, List<String> listaPeriodos);

}