package controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {

	public static ModelAndView show(Request request, Response response) {

		HashMap<Object, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "/cuentas/consultarCuentas.hbs");
	}
}
