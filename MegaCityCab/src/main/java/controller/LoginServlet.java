package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Loginservlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.validateUser(username, password);

        if (user != null) {
            // Set the user in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role
            String role = user.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("/MegaCityCab/views/admindashboard.jsp");
            } else if ("manager".equals(role)) {
                response.sendRedirect("/MegaCityCab/views/managerdashboard.jsp");
            } else {
                response.sendRedirect("/MegaCityCab/views/homedashboard.jsp");  // Default to user dashboard
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid Credentials");
        }
    }
}  