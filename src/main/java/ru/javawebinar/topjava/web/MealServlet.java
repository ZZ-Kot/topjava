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

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        
        List<MealTo> mealsTo = Arrays.asList(
                new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, false),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, false)
        );

        HttpSession s = request.getSession();
        
        s.setAttribute("mealsTo", mealsTo);
        s.setAttribute("meals", meals);
//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        response.sendRedirect("meals.jsp");
    }
}
