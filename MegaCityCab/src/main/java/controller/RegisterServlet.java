package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form parameters
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String role = "user"; // Default role is "user"
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String nic = request.getParameter("nic");

            // Validate inputs to ensure no fields are empty
            if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                address == null || address.trim().isEmpty() ||
                nic == null || nic.trim().isEmpty()) {
                response.sendRedirect("views/register.jsp?error=Please fill in all fields.");
                return;  // Stop further execution if validation fails
            }

            // Call DAO to register user
            UserDAO dao = new UserDAO();
            boolean success = dao.registerUser(username, password, email, phone, role);

            if (success) {
                // Insert customer info into the database
                int customerId = dao.addCustomer(name, address, nic, phone);
                if (customerId > 0) {
                    response.sendRedirect("views/login.jsp?msg=Registration Successful");
                } else {
                    response.sendRedirect("views/register.jsp?error=Customer registration failed");
                }
            } else {
                response.sendRedirect("views/register.jsp?error=Registration Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("views/register.jsp?error=An error occurred. Please try again.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}

