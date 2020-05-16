package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class testCon {
	
	private String url = "jdbc:mysql://localhost:3306/BookStore?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "Sriyal.meh21";
    private Connection con = null;
    
    public testCon () {
    	    	
    }
    
    public void testQuery () {
    	
    	String insertQuery = "SELECT `AuthorID` FROM  BookStore.Authors";
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver") ;
    	    con = DriverManager.getConnection(url, user, password);
    	    Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(insertQuery);
                
           // loop through the result set
           while (rs.next()) {
               System.out.println(rs.getInt("AuthorID"));
                   
           }  	    
    	    
    	}
    	catch (Exception e) {
    		System.out.println (e.getMessage());
    	}
    	
    }

}
