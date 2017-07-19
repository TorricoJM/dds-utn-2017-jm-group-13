package methodologies;

import java.util.List;
import criterios.CriterioComparativo;
import criterios.CriterioTaxativo;

import org.uqbar.commons.utils.Observable;

@Observable
public class DataMetodologia {
	private String nombre;
	private List<CriterioComparativo> listaComparativos;
	private List<CriterioTaxativo> listaTaxativos;
	
	public DataMetodologia(String nombre, List<CriterioComparativo> listaComparativos, List<CriterioTaxativo> listaTaxativos){
		this.setNombre(nombre);
		this.setListaComparativos(listaComparativos);
		this.setListaTaxativos(listaTaxativos);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CriterioComparativo> getListaComparativos() {
		return listaComparativos;
	}

	public void setListaComparativos(List<CriterioComparativo> listaComparativos) {
		this.listaComparativos = listaComparativos;
	}

	public List<CriterioTaxativo> getListaTaxativos() {
		return listaTaxativos;
	}

	public void setListaTaxativos(List<CriterioTaxativo> listaTaxativos) {
		this.listaTaxativos = listaTaxativos;
	}

	
	
}
