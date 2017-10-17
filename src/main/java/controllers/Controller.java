package controllers;

import spark.Request;
import spark.Response;

public abstract class Controller{
	protected static void setearRutaAnterior(Request request, Response response) {
		String path = request.url().substring(21);
		response.cookie("urlFrom", path);
	}
}