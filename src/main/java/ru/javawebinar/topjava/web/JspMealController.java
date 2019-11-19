package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

/*
 * 1.3 Удалить сервлеты и перенести функциональность MealServlet в JspMealController контроллер
 * (по аналогии с RootController). MealRestController у нас останется, с ним будем работать позже.
 * 1.3.1 разнести запросы на update/delete/.. по разным методам (попробуйте вообще без action=).
 * 		Можно по аналогии с RootController#setUser принимать HttpServletRequest request
 * 		(аннотации на параметры и адаптеры для LocalDate/Time мы введем позже).
 * 1.3.2 в одном контроллере нельзя использовать другой. Чтобы не дублировать код, можно сделать
 * 		наследование контроллеров от абстрактного класса.
 * 1.3.3 добавить локализацию и jsp:include в mealForm.jsp / meals.jsp
 * */
@Controller
//@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

	@Autowired
	private MealService service;

	public JspMealController(MealService service) {
		super(service);
	}

//	@GetMapping("/")
//	public String root() {
//		return "meals";
//	}

	@PostMapping("/meals")
	public void getMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action == null ? "all" : action) {
		case "delete":
			int id = getId(request);
			delete(id);
			response.sendRedirect("meals");
			break;
		case "create":
		case "update":
			final Meal meal = "create".equals(action) ?
					new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
						get(getId(request));
					request.setAttribute("meal", meal);
					request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
					break;
		case "filter":
			LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
			LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
			LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
			LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
			request.setAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
			request.getRequestDispatcher("/meals.jsp").forward(request, response);
			break;
		case "all":
		default:
			request.setAttribute("meals", getAll());
			request.getRequestDispatcher("/meals.jsp").forward(request, response);
			break;
		}
	}

	//    @PostMapping("/{id}")
	//    public String getMeal(@PathVariable("id") Integer id, Model model) {
	//    	model.addAttribute("meal", super.get(id));
	//        return "meals";
	//    }

	//    @PostMapping("/delete/{id}")
	//    public String deleteMeal(@PathVariable("id") Integer id, HttpServletRequest request) {
	//    	super.delete(id);
	//        return "meals";
	//    }
	//    
	//    @PostMapping("/create/{id}")
	//    public String createMeal(HttpServletRequest request) {
	//    	return "meals";
	//    }
	//
	//    @PostMapping("/update/{id}")
	//    public String updateMeal(HttpServletRequest request) {
	//    	return "meals";
	//    }
	//
	//    @PostMapping("/filter/{id}")
	//    public String filterMeal(HttpServletRequest request) {
	//    	return "meals";
	//    }
	//
	//    @PostMapping("/all")
	//    public String allMeal(HttpServletRequest request) {
	//    	return "meals";
	//    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
