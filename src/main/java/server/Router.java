package server;

import controllers.CuentasController;
import controllers.LoginController;
import controllers.MenuController;
import spark.Spark;
import spark.TemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {

		TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
		
		Spark.staticFiles.location("/public");
		
		Spark.get("/", LoginController::home, engine);
		Spark.post("/login", LoginController::login);
		Spark.get("/menu", MenuController::menu, engine);
		Spark.get("/menu/cuentas", CuentasController::show, engine);
	}

}
