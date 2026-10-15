
package com.Student;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/Registration")
public class Home extends GenericServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String course = request.getParameter("course");
        String address = request.getParameter("address");

        out.println("<html><body>");
        out.println("<h1>Student Registration Details</h1>");

        out.println("<h2>ID: " + id + "</h2>");
        out.println("<h2>Name: " + name + "</h2>");
        out.println("<h2>Email: " + email + "</h2>");
        out.println("<h2>Gender: " + gender + "</h2>");
        out.println("<h2>Phone: " + phone + "</h2>");
        out.println("<h2>Course: " + course + "</h2>");
        out.println("<h2>Address: " + address + "</h2>");

        out.println("</body></html>");
        
        

final String URL = "jdbc:mysql://localhost:3306/stackly";
final String USER = "root";
final String PASSWORD = "123456";

String query = "INSERT INTO Student "
        + "(id, name, email, gender, phone, course, address) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

try {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection c = DriverManager.getConnection(URL, USER, PASSWORD);

    PreparedStatement pr = c.prepareStatement(query);

    pr.setString(1, id);
    pr.setString(2, name);
    pr.setString(3, email);
    pr.setString(4, gender);
    pr.setString(5, phone);
    pr.setString(6, course);
    pr.setString(7, address);

    int rows = pr.executeUpdate();

    if (rows > 0) {
    	out.println("<h1>" + rows +"inserted </h2>");
    }

    pr.close();
    c.close();

} catch (Exception e) {
    e.printStackTrace();
    out.println("<h2>Error: " + e.getMessage() + "</h2>");
}
                
    }
}
