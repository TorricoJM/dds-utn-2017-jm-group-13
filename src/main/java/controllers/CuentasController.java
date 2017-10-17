package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cuenta;
import repositories.RepositorioCuentas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController extends Controller {
	
	public static ModelAndView listar(Request request, Response response) {

		Map<String, Object> model = new HashMap<>();
		List<Cuenta> cuentas = RepositorioCuentas.getInstance().getElementos();

		model.put("Cuentas", cuentas);

		return new ModelAndView(model, "cuentas/cuentas.hbs");
	}
}
