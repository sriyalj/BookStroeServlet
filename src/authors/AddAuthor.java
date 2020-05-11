package authors;


import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

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
		
		if (request.getParameter("FirstNumber") == null && request.getParameter("SecondNumber") == null && request.getParameter("Operation") == null ) {
			String data = "";   
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			data = builder.toString();					
			/*
			Controller control = new Controller (data);
			ArrayList <String> ans = control.doPost();
	        
	        for (int cnt = 0; cnt < ans.size(); cnt++) {
	        	response.getWriter().println(ans.get(cnt));
	         }
	         */
		}
		else {
			doGet(request, response);		
		}			
	}
}
