package edu.pnu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet01 extends HttpServlet {

	//1 .default serial version id 추가
	private static final long serialVersionUID = 1L;

	//2. control + space 에서 method 추가
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MyServlet01");
	}
}
