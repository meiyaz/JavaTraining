package com.training;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HtmlClass extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String className = request.getParameter("class");
		// response.setStatus(500);
		
		// plain text
//		response.setContentType("text/plain");
//		response.getWriter().append("Hi, " + name + " / " + age + ".. Welcome to " + className +" class...");
		
		// html
//		response.setContentType("text/html");
//		response.getWriter().append("<html><body><h1>Hi, " + name + " / " + age + "..</h1><h3>Welcome to " + className +" class...</h3></body></html>");
		
		// json
		response.setContentType("application/json");
		response.getWriter().println("{\"name\": \""+name+"\",\"age\": \""+age+"\",\"class\": \""+className+"\"}");
	}
}