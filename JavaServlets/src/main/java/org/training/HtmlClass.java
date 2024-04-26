package org.training;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.JSONObject;
import org.utils.CommonUtils;

/*
 * @author: meiyaz
 */
public class HtmlClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read query params or form-encode
		String className = request.getParameter("class");
		
		// read json input
		JSONObject inputData = new CommonUtils().readJSONInput(request.getReader());
		String name = inputData.getString("name");
		String age = inputData.getString("age");

		// status
		// response.setStatus(500);
		
		// plain text
//		response.setContentType("text/plain");
//		response.getWriter().append("Hi, " + name + " / " + age + ".. Welcome to " + className +" class...");
		
		// html
//		response.setContentType("text/html");
//		response.getWriter().append("<html><body><h1>Hi, " + name + " / " + age + "..</h1><h3>Welcome to " + className +" class...</h3></body></html>");
		
		// json
		response.setContentType("application/json");
//		response.getWriter().println("{\"name\": \""+name+"\",\"age\": \""+age+"\",\"class\": \""+className+"\"}");
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		obj.put("class", className);
		obj.put("jsonData", inputData);
		response.getWriter().println(obj);
	}
	
}
