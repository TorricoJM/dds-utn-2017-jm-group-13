package criterios;

public class CriterioComparativo implements Criterio{
	
	public String empresa1;
	public String empresa2;
	
	public CriterioComparativo(String empresa1,String empresa2){
		this.empresa1 = empresa1;
		this.empresa2 = empresa2;
	}
	
	public Boolean evaluar(){
		return false;
	}
}