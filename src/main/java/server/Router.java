package server;

import controllers.*;
import spark.Spark;
import spark.TemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {

		TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
		
		Spark.staticFiles.location("/public");
		
		Spark.before((request, response) -> {
			if(request.session().attribute("user") == null
					&& !esRutaPublica(request.pathInfo())) {
				request.session().attribute("urlAnterior",request.pathInfo());
				response.redirect("/login");
			}
		});
		
		Spark.get("/", HomeController::home, engine);
		Spark.get("/home", HomeController::home, engine);
		Spark.get("/login", LoginController::home, engine);
		Spark.post("/login", LoginController::login);
		
		Spark.get("/periodos", IndicadoresController::buscarPeriodos, engine);
		
		Spark.get("/empresas", EmpresasController::listar,engine);
		Spark.get("/empresas/consultaCuentas", EmpresasController::consultarCuentas,engine);
		
		Spark.get("/indicadores", IndicadoresController::listar,engine);
		Spark.get("/indicadores/consulta", IndicadoresController::consultar,engine);
		Spark.get("/indicadores/resultadoConsulta", IndicadoresController::resultadoConsulta,engine);
		Spark.get("/indicadores/new", IndicadoresController::home,engine);
		Spark.post("/indicadores/new", IndicadoresController::crear);
		
		Spark.get("/metodologias", MetodologiasController::home,engine);
		Spark.post("/metodologias", MetodologiasController::listar,engine);
		Spark.get("/metodologias/new", MetodologiasController::crear,engine);
		
		Spark.get("/cuentas", CuentasController::listar,engine);
	}
	
	public static boolean esRutaPublica(String ruta) {
		return ruta.equals("/") || ruta.equals("/home") || ruta.equals("/login");
	}

}
