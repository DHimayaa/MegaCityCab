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

        // Authenticate the user
        UserDAO dao = new UserDAO();
        User user = dao.validateUser(username, password);

        if (user != null) {
            // Set the user in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store user in session

            // Debugging output to confirm session creation and user
            System.out.println("Session ID: " + session.getId());
            System.out.println("User " + user.getUsername() + " logged in successfully.");

            // Redirect based on role
            String role = user.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("/MegaCityCab/views/admindashboard.jsp");
            } else if ("manager".equals(role)) {
                response.sendRedirect("/MegaCityCab/views/managerdashboard.jsp");
            } else {
                response.sendRedirect("/MegaCityCab/views/homedashboard.jsp");
            }
        } else {
            // Invalid credentials, redirect to login page with error message
            response.sendRedirect("login.jsp?error=Invalid Credentials");
        }
    }
}

