package Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	public static ModelAndView home(Request request, Response response){
		return new ModelAndView(null, "login/login.html");
	}
}
