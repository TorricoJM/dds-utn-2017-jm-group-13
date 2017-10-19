package controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import methodologies.Metodologia;
import repositories.RepositorioEmpresas;
import repositories.RepositorioMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController{

	public static ModelAndView home(Request request, Response response) {

		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		model.put("user", username);

		model.put("Metodologias", RepositorioMetodologias.getInstance().getElementos());
		model.put("periodos", MetodologiasController.listaPeriodos());

		return new ModelAndView(model, "metodologias/metodologias.hbs");
	}

	public static ModelAndView listar(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		model.put("user", username);

		model.put("metodologia", request.queryParams("metodologia"));
		model.put("periodos", MetodologiasController.listaPeriodos());

		Metodologia metodologiaElegida = RepositorioMetodologias.getInstance()
				.obtenerDesdeNombreUna(request.queryParams("metodologia"));
		
		model.put("resultados",
				metodologiaElegida.aplicarMetodologiaA(RepositorioEmpresas.getInstance().getElementos(),
						MetodologiasController.construirRangoDePeriodosCon(request.queryParams("perIni"),
								request.queryParams("perFin"))));

		return new ModelAndView(model, "metodologias/metodologias.hbs");
	}
	
	public static ModelAndView crear(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("user", request.session().attribute("user"));
		return new ModelAndView(model, "proximamente.hbs");
	}

	private static List<String> listaPeriodos() {
		return RepositorioEmpresas.getInstance().getElementos().stream()
				.flatMap(empresa -> empresa.getPeriodos().stream()).map(periodo -> periodo.getPeriodo()).distinct()
				.sorted().collect(Collectors.toList());
	}

	private static List<String> construirRangoDePeriodosCon(String inicio, String fin) {
		List<String> periodosSeleccionados = new LinkedList<>();

		if (inicio.equals(fin)) {
			periodosSeleccionados.add(inicio);
		} else {
			periodosSeleccionados = MetodologiasController.listaPeriodos().subList(
					MetodologiasController.listaPeriodos().indexOf(inicio),
					MetodologiasController.listaPeriodos().indexOf(fin) + 1);
		}
		
		return periodosSeleccionados;
	}
}
