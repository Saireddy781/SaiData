package com.update;

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

@WebServlet("/Update")
public class UpdateData extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String course = req.getParameter("course");
        String address = req.getParameter("address");

        String url = "jdbc:mysql://localhost:3306/stackly";
        String username = "root";
        String password = "123456";

        String sql = "UPDATE Student SET name=?, email=?, gender=?, "
                   + "phone=?, course=?, address=? WHERE id=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    url, username, password);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setString(5, course);
            ps.setString(6, address);
            ps.setInt(7, Integer.parseInt(id));

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h2>Student data updated successfully!</h2>");
            } else {
                out.println("<h2>No student found with ID: " + id + "</h2>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error updating student data.</h3>");
            e.printStackTrace(out);
        }
    }
}