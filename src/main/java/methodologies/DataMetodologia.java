package methodologies;

import org.uqbar.commons.utils.Observable;

@Observable
public class DataMetodologia {
	private String empresa;
	private String valor;

	public DataMetodologia(String empresa, String valor) {
		this.setEmpresa(empresa);
		this.setValor(valor);
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
