package com.Delete;

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

@WebServlet("/DeleteStudent")
public class DeleteData extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");

        String url = "jdbc:mysql://localhost:3306/stackly";
        String username = "root";
        String password = "123456";

        String sql = "DELETE FROM Student WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    url, username, password);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h2>Student deleted successfully!</h2>");
            } else {
                out.println("<h2>No student found with ID: "
                        + id + "</h2>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error deleting student.</h3>");
            e.printStackTrace(out);
        }
    }
}