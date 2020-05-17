package login;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import DBConn.AuthorDBConn;
import DBConn.LoginDBConn;
import Entity.Author;
import Entity.LoginDetails;
import Util.GeneralServerResponseMsgs;
import Util.ObjectGeneratorFromPayLoad;
import Util.ResponsePayLoadGenerator;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		byte[] message = null;
		LoginDetails loginObj = null;
		GeneralServerResponseMsgs serverResponse = null;
		String reqContentType = "";
		String resContentType = ""; 
		boolean loginStatus = false;
	
		try {
			reqContentType = request.getContentType();
			resContentType = request.getHeader("Accept");
		
			ObjectGeneratorFromPayLoad objGen =  new ObjectGeneratorFromPayLoad ();
		
			if (reqContentType.equals("text/plain; charset=utf-8")) {
				loginObj = (LoginDetails)objGen.getObjectFromText(request);
			}
			else if (reqContentType.equals("application/json; utf-8")) {
				String jsonPayLoad = objGen.getObjectFromJSON(request);
				ObjectMapper mapper = new ObjectMapper();
				loginObj= mapper.readValue(jsonPayLoad, LoginDetails.class);
			} 
			else if (reqContentType.equals("application/xml")) {				
				String xmlPayLoad = objGen.getObjectFromXML(request);
				XmlMapper xmlMapper = new XmlMapper();
				loginObj = xmlMapper.readValue(xmlPayLoad, LoginDetails.class);
			}
			
			LoginDBConn dbCon =  new LoginDBConn ();
			loginStatus = dbCon.userLogin(loginObj);
		    
		    if (loginStatus == true) {
		    	serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_OK),"Login Succesfull. Welcome " + loginObj.getUserName());
		    	Cookie c = new Cookie("userName",dbCon.getrefCode());
		    	c.setDomain("localhost");
		    	//c.setValue("12345");
		    	response.addCookie(c);	
		    	
		    	c = new Cookie("passWord","Sriyal.meh21");
		    	c.setDomain("localhost");
		    	c.setMaxAge(1800);
		    	//c.setValue("12345");
		    	response.addCookie(c);
		    	
		    	ServletContext application = getServletConfig().getServletContext();
		    	String data = dbCon.getrefCode() ;  
		    	application.setAttribute("variable", data); 
		    	application.setAttribute("cnt", Integer.valueOf(1));
		    }
		    else {
		    	serverResponse = new GeneralServerResponseMsgs (Integer.toString(HttpServletResponse.SC_NO_CONTENT),"Login Failed.\n User Name Or Password Is Incorrect");
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
