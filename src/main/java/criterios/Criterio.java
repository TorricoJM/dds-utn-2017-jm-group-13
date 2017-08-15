package criterios;

import java.util.List;

import model.Empresa;

public interface Criterio{
	public String getNombre();

	public Boolean verificarParaUna(Empresa empresa, List<String> periodos);

	Double posicionLuegoDeAplicarDe(Empresa empresa, List<Empresa> empresas, List<String> periodos);
}