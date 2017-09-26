package methodologies;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import criterios.ParComparativoPeso;
import criterios.CriterioTaxativo;
import model.Empresa;

import org.javatuples.*;
import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class Metodologia {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<CriterioTaxativo> criteriosTaxativos;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<ParComparativoPeso> criteriosComparativosPonderacion;

	public Metodologia() {
	}
	
	public Metodologia(String nombre, List<CriterioTaxativo> criteriosTaxativos,
			List<ParComparativoPeso> criteriosComparativos) {
		this.setNombre(nombre);
		this.setCriteriosTaxativos(criteriosTaxativos);
		this.setCriteriosComparativosPonderacion(criteriosComparativos);
	}

	public List<Empresa> aplicarMetodologiaA(List<Empresa> listaEmpresas, List<String> listaPeriodos) {

		List<Empresa> empresasResultantes = this.aplicarCriteriosTaxativos(listaEmpresas, listaPeriodos);

		return this.aplicarCriteriosComparativos(empresasResultantes, listaPeriodos);
	}

	private List<Empresa> aplicarCriteriosComparativos(List<Empresa> empresas, List<String> periodos) {

		return this.establecerPuntajesTotales(empresas, periodos).stream()
				.sorted((e1, e2) -> Double.compare(e1.getValue1(), e2.getValue1())).map(tupla -> tupla.getValue0())
				.collect(Collectors.toList());
	}

	private List<Empresa> aplicarCriteriosTaxativos(List<Empresa> listaEmpresas, List<String> periodos) {

		List<Empresa> empresasResultantes = new LinkedList<>(listaEmpresas);

		return empresasResultantes.stream().filter(empresa -> this.cumpleTodosLosTaxativosUna(empresa, periodos))
				.collect(Collectors.toList());
	}

	private boolean cumpleTodosLosTaxativosUna(Empresa empresa, List<String> periodos) {
		return this.criteriosTaxativos.stream().allMatch(unTaxativo -> unTaxativo.verificarParaUna(empresa, periodos));
	}

	private List<Pair<Empresa, Double>> establecerPuntajesTotales(List<Empresa> empresas, List<String> periodos) {

		return empresas.stream().map(empresa -> this.puntajeTotalDe(empresa, empresas, periodos))
				.collect(Collectors.toList());
	}

	private Pair<Empresa, Double> puntajeTotalDe(Empresa empresa, List<Empresa> empresas, List<String> periodos) {

		return Pair.with(empresa,
				this.getCriteriosComparativosPonderacion().stream()
						.map(criterio -> puntajeParcialDe(empresa, empresas, periodos, criterio))
						.mapToDouble(valor -> new Double(valor)).sum());
	}

	private double puntajeParcialDe(Empresa empresa, List<Empresa> empresas, List<String> periodos,
			ParComparativoPeso criterio) {

		return criterio.getCriterio().posicionLuegoDeAplicarDe(empresa, empresas, periodos) * criterio.getPeso();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ParComparativoPeso> getCriteriosComparativosPonderacion() {
		return this.criteriosComparativosPonderacion;
	}

	public List<CriterioTaxativo> getCriteriosTaxativos() {
		return criteriosTaxativos;
	}

	public void setCriteriosTaxativos(List<CriterioTaxativo> criteriosTaxativos) {
		this.criteriosTaxativos = criteriosTaxativos;
	}

	public void setCriteriosComparativosPonderacion(List<ParComparativoPeso> tuplas) {
		if (tuplas != null)
			this.criteriosComparativosPonderacion = new LinkedList<>(tuplas);
	}
}
