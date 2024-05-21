package com.training;

import java.io.IOException;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InitParams", urlPatterns="/InitParams", initParams = {
		@WebInitParam(name = "name", value = "mei"),
		@WebInitParam(name = "age", value = "25"),
		@WebInitParam(name = "class", value = "java")
})
public class InitParams extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append(getInitParameter("name"));
			response.getWriter().append(getInitParameter("age"));
			response.getWriter().append(getInitParameter("class"));
		} catch(IOException e1) {
			System.out.print("exception occurred");
		}
	}
	
}
