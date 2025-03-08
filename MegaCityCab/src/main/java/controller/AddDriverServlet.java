package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Driver;

import java.io.IOException;

import dao.DriverDAO;

/**
 * Servlet implementation class AddDriverServlet
 */
@WebServlet("/AddDriverServlet")
public class AddDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverName = request.getParameter("driverName");
        String licenseNumber = request.getParameter("licenseNumber");
        String phone = request.getParameter("phone");

        DriverDAO driverDAO = new DriverDAO();
        boolean success = driverDAO.addDriver(new Driver(driverName, licenseNumber, phone));

        if (success) {
            response.sendRedirect("/views/view_drivers.jsp?message=Driver added successfully");
        } else {
            response.sendRedirect("/views/add_driver.jsp?error=Failed to add driver");
        }
    }
}

