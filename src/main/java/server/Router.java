package server;

import controllers.HomeController;
import controllers.IndicadoresController;
import controllers.LoginController;
import spark.Spark;
import spark.TemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {

		TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
		
		Spark.staticFiles.location("/public");
		
		Spark.get("/", HomeController::home, engine);
		
		Spark.get("/login", LoginController::home, engine);
		Spark.post("/login", LoginController::login);
		Spark.get("/indicadores", IndicadoresController::listar,engine);
	}

}
