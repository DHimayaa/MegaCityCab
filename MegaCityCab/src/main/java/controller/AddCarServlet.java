package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Car;

import java.io.IOException;

import dao.CarDAO;

/**
 * Servlet implementation class AddCarServlet
 */
@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carModel = request.getParameter("carModel");
        String carNumber = request.getParameter("carNumber");
        String carType = request.getParameter("carType");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        CarDAO carDAO = new CarDAO();
        boolean success = carDAO.addCar(new Car(carModel, carNumber, carType, capacity));

        if (success) {
            response.sendRedirect("view_cars.jsp?message=Car added successfully");
        } else {
            response.sendRedirect("add_car.jsp?error=Failed to add car");
        }
    }
}
