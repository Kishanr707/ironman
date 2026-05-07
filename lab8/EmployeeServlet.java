package com.pu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.*;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int id = Integer.valueOf(request.getParameter("id"));
		String fn=request.getParameter("firstName");
		String ln=request.getParameter("lastName");
		String un=request.getParameter("username");		
		String pwd=request.getParameter("password");
		String addr=request.getParameter("address");
		String cont=request.getParameter("contact");
		Employee emp = new Employee();
		emp.setId(id);
		emp.setFirstName(fn);
		emp.setLastName(ln);
		emp.setUsername(un);
		emp.setPassword(pwd);
		emp.setAddress(addr);
		emp.setContact(cont);
		request.setAttribute("emp",emp);       
		int result = 0;
	try{
	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","root");
	String query = "INSERT INTO employee1 (id, first_name, last_name, username, password, address, contact) VALUES (?, ?, ?, ?, ?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
preparedStatement.setInt(1, emp.getId());
preparedStatement.setString(2, emp.getFirstName());
preparedStatement.setString(3, emp.getLastName());
preparedStatement.setString(4, emp.getUsername());
preparedStatement.setString(5, emp.getPassword());
preparedStatement.setString(6, emp.getAddress());
preparedStatement.setString(7, emp.getContact());
            System.out.println(preparedStatement);
result = preparedStatement.executeUpdate();
            RequestDispatcher rd=request.getRequestDispatcher("employeedetail.jsp");
rd.forward(request,response);

        } catch (Exception e) {
            System.err.println(e);
        }        

	}

}
