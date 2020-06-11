package test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

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
		
		String userName = request.getHeader("userName");
		String passWd = request.getHeader("passWD");
				
		String data = "";   
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line = "";
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		
		data = builder.toString();	
		int upperbound = 25;
		Random rand = new Random();
		String userID = userName + Integer.valueOf(rand.nextInt(upperbound)).toString();
		
		HttpSession session=request.getSession();
		Date createTime = new Date(session.getCreationTime());
	    Date lastAccessTime = new Date(session.getLastAccessedTime());

	    String title = "";
	    String name = "";
	    Integer visitCount = 1;
	    String visitCountKey = new String("visitCount");

	      // Check if this is new comer on your web page.
	    if (session.isNew()) {
	       title = "Welcome to my website";
	       session.setAttribute("UserIDKey", userID);
	       name = (String)session.getAttribute("UserIDKey");
	    } else {
	       visitCount = (Integer)session.getAttribute(visitCountKey);
	       visitCount = visitCount + 1;
	       name = (String)session.getAttribute("UserIDKey");
	    }
	    session.setAttribute(visitCountKey,  visitCount);
	    
	    //ServletContext application = getServletConfig().getServletContext();
	    //application.setAttribute(name, object);
	      
	    System.out.println ("Title " + title);
	    System.out.println ("Session ID " + session.getId());
	    System.out.println ("Create Time " + createTime);
	    System.out.println ("Last Access " + lastAccessTime);
	    System.out.println ("UserID " + name);
	    System.out.println ("No of Visits " + visitCount.toString());
	    System.out.println ("\n\n");

		OutputStream out = response.getOutputStream(); 
	    out.write(((String)session.getAttribute("UserIDKey")).getBytes()); 

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
 
