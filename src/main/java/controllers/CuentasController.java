package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cuenta;
import repositories.RepositorioCuentas;
import server.AuthenticationFilter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController extends Controller {
	
	public static ModelAndView listar(Request request, Response response) {
		AuthenticationFilter auth = new AuthenticationFilter();
		setearRutaAnterior(request,response);
		auth.isAuthorized(request, response);
		Map<String, Object> model = new HashMap<>();
		List<Cuenta> cuentas = RepositorioCuentas.getInstance().getElementos();

		model.put("Cuentas", cuentas);
		model.put("usuario", auth);
		model.put("username", request.session().attribute("user"));

		return new ModelAndView(model, "cuentas/cuentas.hbs");
	}
}