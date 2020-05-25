package test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBConn.testCon;

@WebServlet("/test/test")
public class test extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println ("Test Servlet doPost Called");	
		
		String Name = request.getParameter("userName"); 
		String passWD = request.getParameter("passWD"); 
		
		String data = "";   
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		data = builder.toString();	
		
		HttpSession session=request.getSession(true);
		
		// Get session creation time.
	      Date createTime = new Date(session.getCreationTime());
	         
	      // Get last access time of this web page.
	      Date lastAccessTime = new Date(session.getLastAccessedTime());

	      String title = "Welcome Back to my website";
	      Integer visitCount = 0;
	      String visitCountKey = new String("visitCount");
	      String userIDKey = new String("userID");
	      String userID = new String("ABCD");

	      // Check if this is new comer on your web page.
	      if (session.isNew()) {
	         title = "Welcome to my website";
	         session.setAttribute(userIDKey, userID);
	      } else {
	         visitCount = (Integer)session.getAttribute(visitCountKey);
	         visitCount = visitCount + 1;
	         userID = (String)session.getAttribute(userIDKey);
	      }
	      session.setAttribute(visitCountKey,  visitCount);

		OutputStream out = response.getOutputStream(); 
	    out.write(data.getBytes()); 

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
 
