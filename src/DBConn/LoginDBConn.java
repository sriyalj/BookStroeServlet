package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entity.UserProfile;

public class LoginDBConn {
	
	private String url = "jdbc:mysql://localhost:3306/BookStore?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "Sriyal.meh21";
    private Connection con = null;
    private String refCode = null;
    
    public LoginDBConn () {
    	
    }
    
    public String getrefCode () {
    	return refCode;
    }
    
    public boolean userLogin (UserProfile obj) throws SQLException{
    	boolean status = false;    	
    	
    	String selectQuery = "SELECT * FROM Users WHERE username = ? and password = ?";
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver") ;
    	    con = DriverManager.getConnection(url, user, password);
    		PreparedStatement preparedStmt = con.prepareStatement(selectQuery);
            preparedStmt.setString(1, obj.getUserName());
            preparedStmt.setString(2, obj.getPassWord());
            ResultSet rs = preparedStmt.executeQuery();           
            
            while ( rs.next() ) {  
            	refCode= rs.getString("refCode");
            	status = true;
            }
            rs.close();
            preparedStmt.close();

    	} catch (ClassNotFoundException e) {
			throw new SQLException("DB Driver Load Failed");		
    	} 
    	finally {
    		if (con != null) {
    			try { 
    				con.close(); 
    			} 
    			catch (SQLException ex) 
    			{
    				Logger lgr = Logger.getLogger(LoginDBConn.class.getName());
    	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
    			}
    		}    	
         }  
    	return status;
    }

}
