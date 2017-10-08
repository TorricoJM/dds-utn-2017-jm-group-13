package server;

import controllers.LoginController;
import spark.Spark;
import spark.TemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {

		TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
		
		Spark.staticFiles.location("/public");
		
		Spark.get("/home", LoginController::home, engine);
		Spark.post("/login", LoginController::login);
	}

}
