package criterios;

public class CriterioTaxativo implements Criterio{
	
	public String empresa;

	@Override
	public Boolean evaluar() {
		return false;
	}

}