package model.parser.objetosParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import model.Cuenta;
import model.parser.IdentificadorInvalidoException;
import repositories.repoArchivos.RepositorioCuentas;
import repositories.repoArchivos.RepositorioIndicadores;

public class ExpresionLexer{
	
	public List<String> identificadores = new LinkedList<>();
	public List<String> operadores = new LinkedList<>();
	public List<ExpresionParser> expresiones = new LinkedList<>();
	
	public List<ExpresionParser> generarArbolExpresiones(String operacion) {
		
		extraerIdentificadores(operacion);
		extraerOperadores(operacion);
		if (identificadores.size() != operadores.size()+1){
			throw new IdentificadorInvalidoException("Identificador con operacion erronea");
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
		try {
			for (String retval: operacion.split("[\\+\\-\\*\\/]")){
				identificadores.add(retval.trim());
			}
		} catch (NullPointerException e){
			throw new IdentificadorInvalidoException("Identificador con operacion erronea");
		}
	}
	
	public void convertirIdentificadoresEnExpresiones(List<String> identificadores) {
		expresiones = identificadores.stream().map(id -> this.identificar(id)).collect(Collectors.toList());
	}

	public ExpresionParser identificar(String id) {
		if (RepositorioCuentas.getInstance().tieneCuentaSegunNombre(new Cuenta(id,""))){
			return new CuentaParser(id);
		}
		else if (RepositorioIndicadores.getInstance().tieneIndicador(id)){
			return new IndicadorParser(id, RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre(id).getOperacion());
		}
		else if (this.idMatcheaCon(id, "^(\\d+\\.)?\\d+$")){
			return new ConstanteParser(id);
		}
		else
			throw new IdentificadorInvalidoException("No se puede identificar a: " + id);
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
			crearOperacionBinaria("*",Operador.MULTIPLICACION,expresiones);
			return;
		}
		else if (operadores.contains("/")){
			crearOperacionBinaria("/",Operador.DIVISION,expresiones);
			return;
		}
		else if (operadores.contains("+")){
			crearOperacionBinaria("+",Operador.SUMA,expresiones);
			return;
		}
		else if (operadores.contains("-")){
			crearOperacionBinaria("-",Operador.RESTA,expresiones);
			return;
		}
		else throw new IdentificadorInvalidoException("Sin operadores para crear operacion");
	}

	public void crearOperacionBinaria(String operador,Operador operacion,List<ExpresionParser> expresiones) {
		int posicion = operadores.indexOf(operador);
		ExpresionParser unaMultiplicacion = 
				new OperacionBinaria(operacion, expresiones.get(posicion)
						, expresiones.get(posicion+1));
		
		agregarExpresion(unaMultiplicacion, posicion);
	}
	
	public void agregarExpresion(ExpresionParser expresion, int posicion){
		expresiones.set(posicion, expresion);
		expresiones.remove(posicion+1);
		operadores.remove(posicion);
	}
}