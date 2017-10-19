package server;

import controllers.*;
import spark.Spark;
import spark.TemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {

		TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
		
		Spark.staticFiles.location("/public");
		
		Spark.get("/", HomeController::home, engine);
		Spark.get("/home", HomeController::home, engine);
		Spark.get("/login", LoginController::home, engine);
		Spark.post("/login", LoginController::login);
		
		Spark.get("/indicadores", IndicadoresController::listar,engine);
		Spark.get("/indicadores/consulta", IndicadoresController::consultar,engine);
		Spark.get("/indicadores/new", IndicadoresController::home,engine);
		Spark.post("/indicadores/new", IndicadoresController::crear);
		
		Spark.get("/metodologias", MetodologiasController::home,engine);
		Spark.get("/metodologias/consulta", MetodologiasController::consulta,engine);
		Spark.get("/metodologias/new", MetodologiasController::home,engine);
		
		Spark.get("/cuentas", CuentasController::listar,engine);
	}

}
