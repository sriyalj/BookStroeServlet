package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entity.Author;

public class AuthorDBConn {
	
	private String url = "jdbc:mysql://localhost:3306/BookStore?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "Sriyal.meh21";
    private Connection con = null;
    
    public AuthorDBConn () {
    	
    }
    
    public boolean addAuthor(Author obj) throws SQLException{
    	
    	boolean success = false;    	
    	
    	String insertQuery = " insert into Authors (Fst_Name, Mdle_Name, Lst_Name, Origin_Country)"
    	        + " values (?, ?, ?, ?)";
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver") ;
    	    con = DriverManager.getConnection(url, user, password);
    		PreparedStatement preparedStmt = con.prepareStatement(insertQuery);
            preparedStmt.setString (1, obj.getFstName());
            preparedStmt.setString (2, obj.getMiddleName());
            preparedStmt.setString (3, obj.getLastName());
            preparedStmt.setString (4, obj.getOriginCountry());
            success = preparedStmt.execute();
           
    	} catch (ClassNotFoundException e) {
			throw new SQLException("DB Driver Load Failed");
		}
    	finally {
    		if (con != null) {
    			try { 
    				con.close(); 
    			} 
    			catch (SQLException ex) {
    				Logger lgr = Logger.getLogger(AuthorDBConn.class.getName());
    	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
    			}
    		}
    	}
    	return success;
    }

}
