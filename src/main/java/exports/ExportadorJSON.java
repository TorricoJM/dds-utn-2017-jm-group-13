package exports;

import adapters.AdaptadorSaliente;

public abstract class ExportadorJSON {

	protected AdaptadorSaliente adaptador;

	public ExportadorJSON(AdaptadorSaliente adaptador){
		this.adaptador = adaptador;
	}
	
	public void exportar() {
		adaptador.guardarEnArchivo(this.parsearContenidoDeRepositorio());
	}

	protected abstract String parsearContenidoDeRepositorio();
}
