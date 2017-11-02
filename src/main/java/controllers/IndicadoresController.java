package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import indicators.DataIndicador;
import indicators.Indicador;
import indicators.IndicadorConResultado;
import model.Empresa;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;
import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import user.User;

public class IndicadoresController{
	
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	public static ModelAndView buscarPeriodos(Request request, Response response) {
		String empresaNombre = request.queryParams("nombre");
		Empresa empresa = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre(empresaNombre);
		Map<String, Object> model = new HashMap<>();
		model.put("periodos", empresa.getPeriodos());
		
		return new ModelAndView(model, "indicadores/generarSelectorPeriodos.hbs");
	}

	public static ModelAndView listar(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		User user = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(username);
		List<Indicador> indicadores = RepositorioIndicadores.getInstance().getElementosByUserID(user.getId());

		model.put("Indicadores", indicadores);
		model.put("user", username);
		return new ModelAndView(model, "indicadores/indicadores.hbs");
	}

	public static ModelAndView consultar(Request request, Response response) {
		List<Empresa> empresas = RepositorioEmpresas.getInstance().getElementos();
		Map<String, Object> model = new HashMap<>();
		model.put("empresas", empresas);
		model.put("user", request.session().attribute("user"));
		return new ModelAndView(model, "indicadores/consultarIndicadores.hbs");
	}
	
	public static ModelAndView resultadoConsulta(Request request, Response response) {
		String empresaNombre = request.queryParams("empresa");
		String periodo = request.queryParams("periodos");
		if(empresaNombre == null) {
			Spark.halt(500, "No se encontró el recurso, joven aventurero");
		}
		long userId = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(request.session().attribute("user")).getId();
		List<Indicador> indicadores = RepositorioIndicadores.getInstance().getElementosByUserID(userId);
		Map<String, Object> model = new HashMap<>();
		List<IndicadorConResultado> resultados = indicadores.stream().map(ind -> new IndicadorConResultado(ind.getNombre(),ind.evaluateEn(empresaNombre, periodo))).collect(Collectors.toList());
		model.put("user", request.session().attribute("user"));
		model.put("resultados", resultados);
		model.put("empresa", empresaNombre);
		model.put("periodo", periodo);
		return new ModelAndView(model, "indicadores/resultadoConsulta.hbs");
	}
	
	public static ModelAndView home(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("user", request.session().attribute("user"));
		return new ModelAndView(model, "indicadores/crearIndicador.hbs");
	}
	
	public static ModelAndView crear(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		String operacion = request.queryParams("operacion");
		User usuario = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(request.session().attribute("user"));
		
		DataIndicador nuevoIndicador = new DataIndicador(nombre, operacion);
		
		usuario.agregarIndicador(nuevoIndicador);
		entityManager.persist(usuario);				//FIXME aca estoy persistiendo
		RepositorioUsuarios.getInstance().agregar(usuario);
		
		response.redirect("/");
		return null;
	}
	
}