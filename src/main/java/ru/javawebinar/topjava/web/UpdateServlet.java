package ru.javawebinar.topjava.web;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

public class UpdateServlet extends HttpServlet {
    private static final Logger log = getLogger(UpdateServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to update");

        synchronized (request) {
			request.setCharacterEncoding("UTF-8");
			String updateRequestParam = request.getParameter("update");
			if (updateRequestParam == null) {
				Long id = Long.parseLong(request.getParameter("id"));
				HttpSession s = request.getSession();
				s.setAttribute("meal", MealsUtil.getOne(id));
				response.sendRedirect("update.jsp");
			} else {
				String description = request.getParameter("description");
				String calories = request.getParameter("calories");
				if (description == null || calories == null || description.isEmpty() || calories.isEmpty()) {
					Long id = Long.parseLong(request.getParameter("id"));
					HttpSession s = request.getSession();
					s.setAttribute("meal", MealsUtil.getOne(id));
					request.getRequestDispatcher("update.jsp").forward(request, response);
				} else {
					Long id = Long.parseLong(request.getParameter("id"));
					LocalDateTime dateTime = LocalDateTime.now();

					MealsUtil.getOne(id).setDateTime(dateTime);
					MealsUtil.getOne(id).setDescription(description);
					MealsUtil.getOne(id).setCalories(Integer.parseInt(calories));
					MealsUtil.getOne(id).setExcess(false);

					response.sendRedirect("meals.jsp");
				}
			}
		}
    }

}
