package ui.viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import methodologies.DataMetodologia;
import repositories.RepositorioMetodologias;

@Observable
public class ConsultarMetodologiasViewModel {
	private List<DataMetodologia> metodologias;
	private DataMetodologia metodologiaSeleccionada;

	public ConsultarMetodologiasViewModel() {
		this.metodologias = RepositorioMetodologias.getInstance().getListaMetodologias();
	}

	public List<DataMetodologia> getmetodologias() {
		return metodologias;
	}

	public void setEmpresas(List<DataMetodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public DataMetodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(DataMetodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}
	
}
