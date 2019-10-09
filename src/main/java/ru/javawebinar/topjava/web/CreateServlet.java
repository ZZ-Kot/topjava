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

public class CreateServlet extends HttpServlet {
	private static final Logger log = getLogger(CreateServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("redirect to doPost");
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("redirect to create");

		synchronized (request) {
			request.setCharacterEncoding("UTF-8");
			String description = request.getParameter("description");
			String calories = request.getParameter("calories");
			if (description == null || calories == null || description.isEmpty() || calories.isEmpty()) {
				response.sendRedirect("create.jsp");
			} else {
				Long id = MealsUtil.mealsCount++;
				LocalDateTime dateTime = LocalDateTime.now();

				MealTo mealTo = new MealTo(id, dateTime, description, Integer.parseInt(calories), false);

				MealsUtil.create(mealTo);
				response.sendRedirect("meals.jsp");
			}
		}
	}

}
