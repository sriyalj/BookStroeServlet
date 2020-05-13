package authors;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConn.AuthorDBConn;
import Entity.Author;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class View
 */
@WebServlet("/authors/addBook")
public class AddAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList <String> res = new ArrayList <> ();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println ("Add Author Servlet doGet Called");
		response.getWriter().println("Add Author Servlet doGet Response");      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		byte[] message = null;
		System.out.println (request.getContentType());
		System.out.println (request.getHeader("Request-Type"));
					
		request.setCharacterEncoding("UTF-8");
	    int contentLen = request.getContentLength();
		InputStream is = request.getInputStream();
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen - readLen);
					if (readLengthThisTime == -1) {
						break;
					}
					readLen += readLengthThisTime;
				}
				ByteArrayInputStream in = new ByteArrayInputStream(message);
			    ObjectInputStream ois = new ObjectInputStream(in);
			    Author authorObj = (Author)ois.readObject();
			    AuthorDBConn dbCon =  new AuthorDBConn ();
			    dbCon.addAuthor(authorObj);
			    response.getWriter().println("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));       		
	            response.getWriter().println("New Author Added Succesfully");
			    
			} catch (IOException | ClassNotFoundException ex ) {
				
				Logger lgr = Logger.getLogger(AuthorDBConn.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            
	            response.getWriter().println("Server Response Code " + Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));       		
	            response.getWriter().println("Service falied to read the data sent");
			} catch (SQLException ex ) {
				Logger lgr = Logger.getLogger(AuthorDBConn.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            
	            response.getWriter().println("Server Response Code " + Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));       		
	            response.getWriter().println("Service falied write the data to the DB");
			}
		}		
		
	}
}
