package server;

import spark.Request;
import spark.Response;

public class AuthenticationFilter{
	
	public void isAuthorized(Request req, Response res) {
		boolean authenticated =
				req.session().attribute("usuario") != null;
		
		if(!isPublic(req.pathInfo()) || !authenticated) {
			res.redirect("/login");
			
		}
		
		
	}
	
	public boolean isPublic(String path) {
		return path.startsWith("/public/");
	}
}