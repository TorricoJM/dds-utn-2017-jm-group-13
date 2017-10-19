package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Empresa;
import model.PeriodoFiscal;
import repositories.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class EmpresasController {
	
	public static ModelAndView listar(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		List<Empresa> empresas = RepositorioEmpresas.getInstance().getElementos();
		model.put("user", request.session().attribute("user"));
		model.put("empresas", empresas);
		return new ModelAndView(model,"empresas/empresas.hbs");
	}
	
	public static ModelAndView consultarCuentas(Request request, Response response){
		String empresaNombre = request.queryParams("empresa");
		if(empresaNombre == null) {
			Spark.halt(500, "No se encontró el recurso, joven aventurero");
		}
		Empresa empresa = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre(empresaNombre);
		List<PeriodoFiscal> periodos = empresa.getPeriodos();
		Map<String, Object> model = new HashMap<>();
		model.put("user",request.session().attribute("user"));
		model.put("empresa",empresa);
		model.put("periodos", periodos);
		return new ModelAndView(model,"empresas/consultarCuentas.hbs");
	}
}