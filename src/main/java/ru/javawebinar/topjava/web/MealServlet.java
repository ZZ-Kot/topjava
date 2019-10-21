package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javawebinar.topjava.controller.AdminRestController;
import ru.javawebinar.topjava.controller.MealRestController;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/*
 * 6: в MealServlet сделать инициализацию Spring, достать MealRestController
 * из контекста и работать с едой через него (как в SpringMain). pom.xml НЕ менять,
 * работаем со spring-context. Сервлет обращается к контролеру, контроллер
 * вызывает сервис, сервис - репозиторий.
 * */
/*
 * учесть, что когда будем работать через Spring MVC, MealServlet удалим,
 * те вся логика должна быть в контроллере
 * */
/*
 * 8: добавить выбор текущего залогиненного пользователя (имитация
 * авторизации, сделать Select с двумя элементами со значениями 1 и 2 в
 * index.html и SecurityUtil.setAuthUserId(userId) в UserServlet).
 * Настоящая атворизация будет через Spring Security позже.
 * */
public class MealServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

	private MealRestController mealRestController;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
			mealRestController = appCtx.getBean(MealRestController.class);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");

		Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id)
				, LocalDateTime.parse(request.getParameter("dateTime"))
				, request.getParameter("description")
				, Integer.parseInt(request.getParameter("calories"))
				, Integer.parseInt(request.getParameter("userId"))
				);

		log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
		if (meal.isNew()) mealRestController.create(meal);
		else mealRestController.update(meal, meal.getId());

		System.out.println("post filter " + getUserId(request));
		request.setAttribute("userId", getUserId(request));
		response.sendRedirect("meals?userId=" + getUserId(request));
//		request.getRequestDispatcher("/meals.jsp?userId=" + getUserId(request)).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action == null ? "all" : action) {
		case "filter":
//			if (request.getParameter("userId") == null || request.getParameter("userId").isEmpty()) {
//				log.info("filter no filter");
//				response.sendRedirect("meals");
//			} else {
				log.info("filter filter");
				request.setAttribute("userId", getUserId(request));
				String dateFrom = request.getParameter("dateFrom");
				String dateTo = request.getParameter("dateTo");
				String timeFrom = request.getParameter("timeFrom");
				String timeTo = request.getParameter("timeTo");
				
				if (dateFrom == null && dateTo == null && timeFrom == null && timeTo == null) {
					log.info("if " + dateFrom + " " + dateTo + " " + timeFrom + " " + timeTo);
					request.setAttribute("userId", getUserId(request));
					response.sendRedirect("meals?userId=" + getUserId(request));
				} else {
					// 20.10.2019 21.10.2019 21:00 22:00
					log.info("else " + dateFrom + " " + dateTo + " " + timeFrom + " " + timeTo);
					SimpleDateFormat sdfDateFrom = new SimpleDateFormat("dd.MM.yyyy");
					try {
						Date dFrom = sdfDateFrom.parse(dateFrom);
						System.out.println(dFrom);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//			}
			break;
		case "delete":
			int id = getId(request);
			log.info("Delete {}", id);
			mealRestController.delete(id);
			request.setAttribute("userId", getUserId(request));
			response.sendRedirect("meals?userId=" + getUserId(request));
//			response.sendRedirect("meals");
			break;
		case "create":
		case "update":
			final Meal meal = "create".equals(action)
			? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, null)
					: mealRestController.getOne(getId(request));
			
			request.setAttribute("meal", meal);
			request.setAttribute("userId", getUserId(request));
			request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
			break;
		case "all":
		default:
			log.info("getAll");
//			log.info("get user id " + getUserId(request) + "");
			if (request.getParameter("userId") == null || request.getParameter("userId").isEmpty()) {
				log.info("all no filter " + request.getParameter("userId"));
				response.sendRedirect("meals");
			} else {
				log.info("all filter");
				request.setAttribute("userId", getUserId(request));
				request.setAttribute("meals",
						MealsUtil.getTos(mealRestController.getAll(getUserId(request)), MealsUtil.DEFAULT_CALORIES_PER_DAY));
				request.getRequestDispatcher("/meals.jsp").forward(request, response);
			}
			break;
		}
	}

	private int getId(HttpServletRequest request) {
		String paramId = Objects.requireNonNull(request.getParameter("id"));
		return Integer.parseInt(paramId);
	}
	
	private int getUserId(HttpServletRequest request) {
		String paramId = Objects.requireNonNull(request.getParameter("userId"));
		return Integer.parseInt(paramId);
	}

}
