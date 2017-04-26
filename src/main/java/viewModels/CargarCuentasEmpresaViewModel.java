package viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import com.google.gson.Gson;

import model.Adapter_Archivos;
import model.Empresa;
import model.PeriodoFiscalDeEmpresa;
import repositories.Repositorios;

@Observable
@Transactional
public class CargarCuentasEmpresaViewModel {
	
	private Empresa empresaSeleccionada;
	private PeriodoFiscalDeEmpresa periodoFiscalAGuardar = new PeriodoFiscalDeEmpresa();
	private List<Empresa> empresas;
	
	private String periodoValorLocal;
	private Float ebitdaValorLocal;
	private Float fdsValorLocal;
	private Float freeCashFlowValorLocal;
	private Float ingresosContinuosValorLocal;
	private Float ingresosDiscontinuosValorLocal;
	
	
	public CargarCuentasEmpresaViewModel() {
		super();
		this.traerEmpresasDelRepo();
	}


	public Empresa getEmpresaSeleccionada() { //TODO hacer los atributos separados
		return empresaSeleccionada;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public String getPeriodoValorLocal() {
		return periodoValorLocal;
	}
	public Float getEbitdaValorLocal() {
		return ebitdaValorLocal;
	}
	public Float getFdsValorLocal() {
		return fdsValorLocal;
	}
	public Float getFreeCashFlowValorLocal() {
		return freeCashFlowValorLocal;
	}
	public Float getIngresosContinuosValorLocal() {
		return ingresosContinuosValorLocal;
	}
	public Float getIngresosDiscontinuosValorLocal() {
		return ingresosDiscontinuosValorLocal;
	}


	public void setPeriodoValorLocal(String periodoValorLocal) {
		this.periodoValorLocal = periodoValorLocal;
	}
	public void setEbitdaValorLocal(Float ebitdaValorLocal) {
		this.ebitdaValorLocal = ebitdaValorLocal;
	}
	public void setFdsValorLocal(Float fdsValorLocal) {
		this.fdsValorLocal = fdsValorLocal;
	}
	public void setFreeCashFlowValorLocal(Float freeCashFlowValorLocal) {
		this.freeCashFlowValorLocal = freeCashFlowValorLocal;
	}
	public void setIngresosContinuosValorLocal(Float ingresosContinuosValorLocal) {
		this.ingresosContinuosValorLocal = ingresosContinuosValorLocal;
	}
	public void setIngresosDiscontinuosValorLocal(Float ingresosDiscontinuosValorLocal) {
		this.ingresosDiscontinuosValorLocal = ingresosDiscontinuosValorLocal;
	}
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}


	private void traerEmpresasDelRepo(){
		this.empresas = Repositorios.listaEmpresas.getEmpresas();
	}
	
	public void guardarDatosDeCuentas(){
		this.unificarPeriodoFiscalParaGuardar();
		this.agregarPeriodoFiscalEn(this.empresaSeleccionada,this.periodoFiscalAGuardar);
		this.guardarCuentasNuevasEnElArchivo(Repositorios.PATH_ARCHIVO_CUENTAS);
	}
	
	private void unificarPeriodoFiscalParaGuardar(){
		periodoFiscalAGuardar.setPeriodo(this.getPeriodoValorLocal());
		periodoFiscalAGuardar.setEbitda(this.getEbitdaValorLocal());
		periodoFiscalAGuardar.setFds(this.getFdsValorLocal());
		periodoFiscalAGuardar.setFreeCashFlow(this.getFreeCashFlowValorLocal());
		periodoFiscalAGuardar.setIngresoNetoOperacionesContinuas(this.getIngresosContinuosValorLocal());
		periodoFiscalAGuardar.setIngresoNetoOperacionesDiscontinuas(this.getIngresosDiscontinuosValorLocal());
	}
	
	private void agregarPeriodoFiscalEn(Empresa empresa, PeriodoFiscalDeEmpresa periodoFiscal){
		int indiceDeEmpresa = Repositorios.listaEmpresas.getEmpresas().indexOf(empresa);
		Repositorios.listaEmpresas.getEmpresas().get(indiceDeEmpresa).agregarPeriodoFiscal(periodoFiscal);
	}
	
	private void guardarCuentasNuevasEnElArchivo(String path){
		Gson gson = new Gson();
		String cadenaAGuardar = gson.toJson(Repositorios.listaEmpresas, model.ListaEmpresas.class);
		new Adapter_Archivos().guardarJsonEnElArchivo(path, cadenaAGuardar);
	}
}