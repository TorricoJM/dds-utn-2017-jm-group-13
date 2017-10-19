package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import repositories.RepositorioCuentas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController{
	
	public static ModelAndView listar(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Set<String> cuentas = RepositorioCuentas.getInstance().getElementos().stream().map(cuenta->cuenta.getNombre()).collect(Collectors.toSet());

		model.put("Cuentas", cuentas);
		model.put("user", request.session().attribute("user"));

		return new ModelAndView(model, "cuentas/cuentas.hbs");
	}
}