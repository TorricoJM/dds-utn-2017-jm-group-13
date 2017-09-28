package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "empresa_id")
	private List<PeriodoFiscal> periodos = new LinkedList<>();

	public Empresa(String nombre) {
		this.nombre = nombre;
	}

	public Empresa() {
	}

	public String getNombre() {
		return nombre;
	}

	public List<PeriodoFiscal> getPeriodos() {
		return periodos;
	}

	public List<Cuenta> getCuentas() {
		return this.getPeriodos().stream().flatMap((peri) -> peri.getCuentas().stream()).collect(Collectors.toList());
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeriodos(List<PeriodoFiscal> periodos) {
		this.periodos = periodos;
	}

	public void agregarUn(PeriodoFiscal nuevoPeriodo) {
		this.periodos.add(nuevoPeriodo);
	}

	public Boolean tieneUn(PeriodoFiscal nuevoPeriodo) {
		return this.getPeriodos().stream()
				.anyMatch((unPeriodo) -> nuevoPeriodo.getPeriodo().equals(unPeriodo.getPeriodo()));
	}

	private PeriodoFiscal obtenerPeriodoDeEmpresaDadaPor(PeriodoFiscal nuevoPeriodo) {
		return this.getPeriodos().stream()
				.filter((unPeriodo) -> nuevoPeriodo.getPeriodo().equals(unPeriodo.getPeriodo())) //logica repetida
				.findFirst().get();
	}

	public PeriodoFiscal obtenerPeriodoDesdeNombre(String periodoNombre) {
		return this.getPeriodos().stream().filter(periodo -> periodo.getPeriodo().equals(periodoNombre)).findFirst()
				.get();
	}

	private void cargarOActualizarUn(PeriodoFiscal nuevoPeriodo) {
		if (this.tieneUn(nuevoPeriodo)) {
			this.obtenerPeriodoDeEmpresaDadaPor(nuevoPeriodo).mergearCon(nuevoPeriodo);
		} else
			this.agregarUn(nuevoPeriodo);
	}
	
	public void mergearCon(Empresa nuevaEmpresa) {
		nuevaEmpresa.getPeriodos().forEach((nuevoPeriodo) -> this.cargarOActualizarUn(nuevoPeriodo));
	}

}