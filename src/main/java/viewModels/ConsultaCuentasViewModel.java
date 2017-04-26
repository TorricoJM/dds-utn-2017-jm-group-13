package viewModels;

import java.util.List;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import model.*;
import repositories.Repositorios;

@Observable
@Transactional
public class ConsultaCuentasViewModel {
	

	private Empresa empresaSeleccionada;
	private PeriodoFiscalDeEmpresa periodoFiscalSeleccionado;
	private List<Empresa> empresas;
	private List<PeriodoFiscalDeEmpresa> periodosFiscalesDeEmpresa;
	
	
	public ConsultaCuentasViewModel() {
		super();
		this.traerEmpresasDelRepo();
	}


	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public PeriodoFiscalDeEmpresa getPeriodoFiscalSeleccionado() {
		return periodoFiscalSeleccionado;
	}
	public List<PeriodoFiscalDeEmpresa> getPeriodosFiscalesDeEmpresa() {
		return periodosFiscalesDeEmpresa;
	}
	
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.periodosFiscalesDeEmpresa = empresaSeleccionada.getPeriodosFiscales();
	}
	public void setEmpresas(List<Empresa> empresas){
		this.empresas = empresas;
	}
	public void setPeriodoFiscalSeleccionado(PeriodoFiscalDeEmpresa periodoFiscalSeleccionado) {
		this.periodoFiscalSeleccionado = periodoFiscalSeleccionado;
	}
	public void setPeriodosFiscalesDeEmpresa(List<PeriodoFiscalDeEmpresa> periodosFiscalesDeEmpresa) {
		this.periodosFiscalesDeEmpresa = periodosFiscalesDeEmpresa;
	}


	private void traerEmpresasDelRepo(){
		this.empresas = Repositorios.listaEmpresas.getEmpresas();
	}
	
	public void mostrarEmpresaSeleccionda(){
		System.out.println(this.empresaSeleccionada.getNombre());
	}
}
