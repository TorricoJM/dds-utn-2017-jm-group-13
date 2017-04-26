package model;

public class PeriodoFiscalDeEmpresa {
	
	private String periodo;
	
	private Float ebitda;
	private Float fds;
	private Float freeCashFlow;
	private Float ingresoNetoOperacionesDiscontinuas;
	private Float ingresoNetoOperacionesContinuas;
	
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Float getEbitda() {
		return ebitda;
	}
	public Float getFds() {
		return fds;
	}
	public Float getFreeCashFlow() {
		return freeCashFlow;
	}
	public Float getIngresoNetoOperacionesDiscontinuas() {
		return ingresoNetoOperacionesDiscontinuas;
	}
	public Float getIngresoNetoOperacionesContinuas() {
		return ingresoNetoOperacionesContinuas;
	}
	public void setEbitda(Float ebitda) {
		this.ebitda = ebitda;
	}
	public void setFds(Float fds) {
		this.fds = fds;
	}
	public void setFreeCashFlow(Float freeCashFlow) {
		this.freeCashFlow = freeCashFlow;
	}
	public void setIngresoNetoOperacionesDiscontinuas(Float ingresoNetoOperacionesDiscontinuas) {
		this.ingresoNetoOperacionesDiscontinuas = ingresoNetoOperacionesDiscontinuas;
	}
	public void setIngresoNetoOperacionesContinuas(Float ingresoNetoOperacionesContinuas) {
		this.ingresoNetoOperacionesContinuas = ingresoNetoOperacionesContinuas;
	}
}