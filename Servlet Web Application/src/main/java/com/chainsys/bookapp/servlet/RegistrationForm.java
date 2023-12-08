package com.chainsys.bookapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationForm")
public class RegistrationForm extends HttpServlet {
	
	private static final String query ="insert into registrationform(FirstName,LastName,Email,Password,ConfirmPassword) values(?,?,?,?,?)";
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//get writter
    	  PrintWriter pw = resp.getWriter();
    	  //set content type
    	  resp.setContentType("text/html");
    	  //get Register Info
    	  String firstName = req.getParameter("firstName");
    	  String lastName = req.getParameter("lastName");
    	  String email = req.getParameter("email");
    	  String password = req.getParameter("password");
    	  String confirmPassword = req.getParameter("confirmPassword");
    	  
    	  //get load Jdbc Driver
    	  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			// TODO Auto-generated catch block
			cnfe.printStackTrace();
			
		}
    	  try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chainsys?user=root&password=irfani");
  				PreparedStatement ps = con.prepareStatement(query);){
  			ps.setString(1, firstName);
  			ps.setString(2, lastName);
  			ps.setString(3, email);
  			ps.setString(4, password);
  			ps.setString(5, confirmPassword);
  			int count = ps.executeUpdate();
  			if (count==1) {
  				pw.println("<h2>Registration is Successfully</h2>");
  			} else {
  				pw.println("<h2>Registration is Not Registered</h2>");
  			}
  			
  		}catch (SQLException se) {
  			se.printStackTrace();
  			pw.println("<h1>"+se.getMessage()+"</h1>");
  		}catch (Exception e) {
  			e.printStackTrace();
  			pw.println("<h1>"+e.getMessage()+"</h1>");
  		}

    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	doGet(req, resp);
    }
}
