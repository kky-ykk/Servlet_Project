package in.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
	static String url="jdbc:mysql://localhost/student";
	static String userName="root";
	static String password="root123";
	
	static Connection con=null;
	ResultSet r=null;
	Statement s=null;
	public static Connection getCon() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public static void closeObjects(ResultSet r,Statement s,Connection c) throws SQLException {
		if(r!=null)
			r.close();
		if(s!=null)
			s.close();
		if(c!=null)
			c.close();
	}
}
