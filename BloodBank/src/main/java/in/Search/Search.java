package in.Search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.Utility.Conn;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con=null;
	PreparedStatement pmt=null;
	ResultSet rs=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String bg=request.getParameter("bg");
		String location=request.getParameter("location");
		
		String query="select * from bloodDonor where bg=? and location=?;";
		
		out.println("<head><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\"><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js\"></script><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script></head>");
		out.println("<body style='background:lightgreen;'>");
		
		out.println("<table class='table table-striped'>");
		out.println("<tr>");
		out.println("<th class='bg-dark'>Name</th>");
		out.println("<th class='bg-secondary'>Age</th>");
		out.println("<th class='bg-secondary'>Blood Group</th>");
		out.println("<th class='bg-secondary'>Location</th>");
		out.println("<th></th>");
		out.println("</tr>");
		
		try {
			con=Conn.getCon();
			
			if(con!=null) {
				pmt=con.prepareStatement(query);
				if(pmt!=null) {
					pmt.setString(1, bg);
					pmt.setString(2, location);
					
					rs=pmt.executeQuery();
					if(rs!=null) {
						while(rs.next()) {
							out.println("<tr>");
						
							out.println("<td>"+rs.getString(2)+"</td>");
							out.println("<td>"+rs.getInt(3)+"</td>");
							out.println("<td>"+rs.getString(4)+"</td>");
							out.println("<td>"+rs.getString(5)+"</td>");
							out.println("<td><button class='btn-primary'>CONTACT</button>");
							out.println("</tr>");
						}
					}else
						out.println("noo record");
				}else
					out.println("not records");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("</table>");
			
		out.close();
	}

}
