package authors;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import DBConn.AuthorDBConn;
import Entity.Author;
import Entity.LoginDetails;
import Util.GeneralServerResponseMsgs;
import Util.ObjectGeneratorFromPayLoad;
import Util.ResponsePayLoadGenerator;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class View
 */
@WebServlet("/authors/addBook")
public class AddAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		byte[] message = null;
		Author authorObj = null;
		GeneralServerResponseMsgs serverResponse = null;
		String reqContentType = "";
		String resContentType = ""; 
	
		try {
			reqContentType = request.getContentType();
			resContentType = request.getHeader("Accept");
		
			ObjectGeneratorFromPayLoad objGen =  new ObjectGeneratorFromPayLoad ();
		
			if (reqContentType.equals("text/plain; charset=utf-8")) {
				authorObj = (Author)objGen.getObjectFromText(request);
			}
			else if (reqContentType.equals("application/json; utf-8")) {
				String jsonPayLoad = objGen.getObjectFromJSON(request);
				ObjectMapper mapper = new ObjectMapper();
				authorObj = mapper.readValue(jsonPayLoad, Author.class);
			} 
			else if (reqContentType.equals("application/xml")) {
				String xmlPayLoad = objGen.getObjectFromXML(request);
				XmlMapper xmlMapper = new XmlMapper();
				authorObj = xmlMapper.readValue(xmlPayLoad, Author.class);
			}
			
			AuthorDBConn dbCon =  new AuthorDBConn ();
		    boolean status = dbCon.addAuthor(authorObj);
		    
		    if (status == true) {
		    	serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_OK),"New Author Added Succesfully");
		    }
		    else {
		    	serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR),"Failed To Save New Author Details");
		    }
		}			
		catch (IOException | ClassNotFoundException ex ) {
			
			Logger lgr = Logger.getLogger(AuthorDBConn.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR),"Service falied to read the data sent");
            
		} catch (SQLException ex ) {
			Logger lgr = Logger.getLogger(AuthorDBConn.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            
            serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR),"Service falied write the data to the DB");            
		}
		catch (Exception ex ) {
			Logger lgr = Logger.getLogger(AuthorDBConn.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            
            serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR),"Genreal Error At Server");
		}
		finally {
			
			ResponsePayLoadGenerator responsePayLoadGen =  new ResponsePayLoadGenerator ();
			
			if (resContentType.equals("text/plain; charset=utf-8")) {
				byte responsePayLoad [] = responsePayLoadGen.textPayLoadGenerator(serverResponse);				
				OutputStream out = response.getOutputStream(); 
			    out.write(responsePayLoad);
				
			}
			else if (resContentType.equals("application/json; utf-8")) {
				byte responsePayLoad [] = responsePayLoadGen.jsonPayLoadGenerator(serverResponse);
				OutputStream out = response.getOutputStream(); 
			    out.write(responsePayLoad);
			} 
			else if (resContentType.equals("application/xml")) {
				byte responsePayLoad [] = responsePayLoadGen.xmlPayLoadGenerator(serverResponse);
				OutputStream out = response.getOutputStream(); 
			    out.write(responsePayLoad);
			}
			
		}
		
	}
}


/*
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
 */

/* 
  response.getWriter().println("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));       		
            response.getWriter().println("New Author Added Succesfully");
            
  response.getWriter().println("Server Response Code " + Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));       		
            response.getWriter().println("Service falied to read the data sent");
 */
  
