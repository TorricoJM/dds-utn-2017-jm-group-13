package ui.viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;
import model.Empresa;
import model.PeriodoFiscal;
import repositories.repoArchivos.RepositorioEmpresas;

@Observable
public class ConsultarCuentasViewModel {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private PeriodoFiscal periodoSeleccionado;

	public ConsultarCuentasViewModel() {
		this.empresas = RepositorioEmpresas.getInstance().getElementos();
	}

	// -------------------------------------------GETTERS AND SETTERS
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public PeriodoFiscal periodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(PeriodoFiscal periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}
	// --------------------------------------------/GETTERS AND SETTERS

}
