package test;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConn.testCon;

@WebServlet("/test/test")
public class test extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println ("Add Author Servlet doGet Called");
		response.getWriter().println("Add Author Servlet doGet Response");      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println ("Test Servlet doPost Called");
		ServletContext application = getServletConfig().getServletContext();  
		String data_rtrvd= (String) application.getAttribute("variable");
		Integer cnt = (Integer) application.getAttribute("cnt");
		cnt = cnt + 1;
		data_rtrvd = data_rtrvd + cnt.toString();
		System.out.println (data_rtrvd);		

		OutputStream out = response.getOutputStream(); 
	    out.write(data_rtrvd.getBytes()); 

	}
}
