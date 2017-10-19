package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import indicators.DataIndicador;
import indicators.Indicador;
import model.Empresa;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;
import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import user.User;

public class IndicadoresController {

	public static ModelAndView listar(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		User user = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(username);
		List<Indicador> indicadores = RepositorioIndicadores.getInstance().getElementosByUserID(user.getId());

		model.put("Indicadores", indicadores);
		model.put("user", username);
		return new ModelAndView(model, "indicadores/indicadores.hbs");
	}

	public static ModelAndView consulta(Request request, Response response) {

		Map<String, Object> model = new HashMap<>();

		model.put("Indicadores", RepositorioIndicadores.getInstance().getElementos());
		model.put("empresas", RepositorioEmpresas.getInstance().getElementos());
		model.put("periodos", IndicadoresController.listaPeriodos());

		model.put("user", request.session().attribute("user"));
		return new ModelAndView(model, "indicadores/consultarIndicadores.hbs");
	}

	public static ModelAndView resultadoConsulta(Request request, Response response) {

		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		model.put("user", username);

		model.put("Indicadores", RepositorioIndicadores.getInstance().getElementos());
		model.put("empresas", RepositorioEmpresas.getInstance().getElementos());
		model.put("periodos", IndicadoresController.listaPeriodos());

		Indicador indicadorElegido = RepositorioIndicadores.getInstance()
				.obtenerIndicadorDesdeNombre(request.queryParams("indicador"));
		
		Empresa empresaElegida = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre(request.queryParams("empresaElegida"));
		String periodoElegido = request.queryParams("periodo");

		model.put("resultado", indicadorElegido.evaluateEn(empresaElegida.getNombre(), periodoElegido));

		return new ModelAndView(model, "indicadores/consultarIndicadores.hbs");

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
		RepositorioUsuarios.getInstance().agregar(usuario);
		response.redirect("/");
		return null;
	}

	private static List<String> listaPeriodos() {
		return RepositorioEmpresas.getInstance().getElementos().stream()
				.flatMap(empresa -> empresa.getPeriodos().stream()).map(periodo -> periodo.getPeriodo()).distinct()
				.sorted().collect(Collectors.toList());
	}

}
