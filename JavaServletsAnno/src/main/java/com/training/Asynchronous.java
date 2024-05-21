package com.training;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Asynchronous", urlPatterns="/async", asyncSupported = true)
public class Asynchronous  extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println("hello world!!");
		} catch(IOException e1) {
			System.out.print("exception occurred");
		}
	}
	
}
