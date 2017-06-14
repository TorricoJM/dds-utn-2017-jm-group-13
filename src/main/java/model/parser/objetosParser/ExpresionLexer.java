package model.parser.objetosParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioCuentas;
import repositories.RepositorioIndicadores;

public class ExpresionLexer{
	
	public List<String> identificadores = new LinkedList<>();
	public List<String> operadores = new LinkedList<>();
	public List<ExpresionParser> expresiones = new LinkedList<>();
	
	public List<ExpresionParser> generarArbolExpresiones(String operacion) {
		
		extraerIdentificadores(operacion);
		extraerOperadores(operacion);
		if (identificadores.size() != operadores.size()+1){
			//TODO Lanzar excepcion
		}
		convertirIdentificadoresEnExpresiones(identificadores);
		return this.crearArbol();
		
	}

	public void extraerOperadores(String operacion) {
		for (String retval: operacion.split("[^\\+\\-\\*\\/]")){
			operadores.add(retval.trim());
		}

		operadores.removeAll(Arrays.asList(""));
	}

	public void extraerIdentificadores(String operacion) {
		for (String retval: operacion.split("[\\+\\-\\*\\/]")){
			identificadores.add(retval.trim());
		}
	}
	
	public void convertirIdentificadoresEnExpresiones(List<String> identificadores) {
		expresiones = identificadores.stream().map(id -> this.identificar(id)).collect(Collectors.toList());
	}

	public ExpresionParser identificar(String id) {
		if (RepositorioCuentas.getInstance().tieneCuenta(id)){
			return new CuentaParser(id);
		}
		else if (RepositorioIndicadores.getInstance().tieneIndicador(id)){
			return new IndicadorParser(id);
		}
		else if (this.idMatcheaCon(id, "^(\\d+\\.)?\\d+$")){
			return new ConstanteParser(id);
		}
		else
			throw new ErrorEvaluacionException("Error: no se puede identificar a: " + id);
	}

	public boolean idMatcheaCon(String nombre, String regex) {
		final String Regex = regex;
		final String input = nombre;
		Pattern patron;
		Matcher matcheador;
		patron = Pattern.compile(Regex);
		matcheador = patron.matcher(input);
		return matcheador.matches();
	}
	
	public List<ExpresionParser> crearArbol(){
		do {
			crearOperacion(expresiones);
		} while (operadores.size() != 0);
		return expresiones;
	}

	public void crearOperacion(List<ExpresionParser> expresiones) {
		if (operadores.contains("*")){
			crearMultiplicacion(expresiones);
			return;
		}
		else if (operadores.contains("/")){
			crearDivision(expresiones);
			return;
		}
		else if (operadores.contains("+")){
			crearSuma(expresiones);
			return;
		}
		else if (operadores.contains("-")){
			crearResta(expresiones);
			return;
		}
		else throw new ErrorEvaluacionException("Sin operadores para crear operacion");
	}

	public void crearMultiplicacion(List<ExpresionParser> expresiones) {
		int posicion = operadores.indexOf("*");
		ExpresionParser unaMultiplicacion = 
				new MultiplicacionParser(expresiones.get(posicion)
						, expresiones.get(posicion+1));
		
		agregarExpresion(unaMultiplicacion, posicion);
	}
	
	public void crearDivision(List<ExpresionParser> expresiones) {
		int posicion = operadores.indexOf("/");
		ExpresionParser unaDivision = 
				new DivisionParser(expresiones.get(posicion)
						, expresiones.get(posicion+1));
		
		agregarExpresion(unaDivision, posicion);
	}
	
	public void crearSuma(List<ExpresionParser> expresiones){
		int posicion = operadores.indexOf("+");
		ExpresionParser unaSuma = 
				new SumaParser(expresiones.get(posicion)
						, expresiones.get(posicion+1));
		
		agregarExpresion(unaSuma, posicion);
	}
	
	public void crearResta(List<ExpresionParser> expresiones){
		int posicion = operadores.indexOf("-");
		ExpresionParser unaResta = 
				new RestaParser(expresiones.get(posicion)
						, expresiones.get(posicion+1));
		
		agregarExpresion(unaResta, posicion);
	}
	public void agregarExpresion(ExpresionParser expresion, int posicion){
		expresiones.set(posicion, expresion);
		expresiones.remove(posicion+1);
		operadores.remove(posicion);
	}
}