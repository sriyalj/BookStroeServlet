package test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBConn.testCon;

@WebServlet("/test/test2")
public class test2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println ("Test2 Servlet doPost Called");		
		
		String data = "";   
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}		
		data = builder.toString();						
		HttpSession session = request.getSession();
		
		System.out.println ("Tryint To Print All Attributes :");
		
		Enumeration<String> enumeration = session.getAttributeNames();
		
	    while (enumeration.hasMoreElements()) {
		   System.out.println (enumeration.nextElement());
		}
		  
		OutputStream out = response.getOutputStream(); 
		
		if (session.getAttribute("UserIDKey") == null) {
			System.out.println ("Key Is Invalid");
			out.write("Key Is Invalid".getBytes());
		}
		else {
			System.out.println (session.getAttribute("UserIDKey"));
			out.write(((String)session.getAttribute("UserIDKey")).getBytes());
		}
	}
}

/*
 * ServletContext application = getServletConfig().getServletContext();  
		String data_rtrvd= (String) application.getAttribute("variable");
		Integer cnt = (Integer) application.getAttribute("cnt");
		cnt = cnt + 1;
		data_rtrvd = data_rtrvd + cnt.toString();
		System.out.println (data_rtrvd);
 */
 
