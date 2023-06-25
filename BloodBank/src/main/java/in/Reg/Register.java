package in.Reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.Utility.Conn;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con=null;
	PreparedStatement pmt=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		Integer age=Integer.parseInt(request.getParameter("age"));
		String bg=request.getParameter("bg");
		String location=request.getParameter("location");
		int row=0;
		
		try {
			con=Conn.getCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String query="insert into bloodDonor (name,age,bg,location) values(?,?,?,?);";
		
		try {
			pmt=con.prepareStatement(query);
			
			if(pmt!=null) {
				pmt.setString(1, name);
				pmt.setInt(2, age);
				pmt.setString(3, bg);
				pmt.setString(4, location);
				
				row=pmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("<body style='background:lightgreen;'>");
		if(row!=0)
			out.println("<h1 style='text-align:center;'>Register Successfull!</h1>");
		else
			out.println("<h1>Register fail!<h1>");
		
		out.close();
	}

}
