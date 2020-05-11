package DBConn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConn {
	
	private String url = "jdbc:mysql://localhost:3306/BookStore?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "Sriyal.meh21";
    private Connection con = null;
    
    DBConn () {
    	
    }
    
    int saveBook () {
    	return 0;
    }

}
