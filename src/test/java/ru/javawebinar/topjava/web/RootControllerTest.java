package ru.javawebinar.topjava.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.javawebinar.topjava.MealTestData.MEALS_TO;
//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
//import static org.hamcrest.Matchers.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

import java.util.List;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;

import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;

/*
 * 1: Добавить тесты контроллеров:
 * 1.1 RootControllerTest.testMeals для meals.jsp
 * */
class RootControllerTest extends AbstractControllerTest {

	@Test
	public void getMeals() throws Exception {
		mockMvc.perform(get("/meals"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("meals"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
			.andExpect(model().attribute("meals",
					new AssertionMatcher<List<MealTo>>() {
						@Override
						public void assertion(List<MealTo> actual) throws AssertionError {
							MealTestData.assertMatchMealTo(actual, MEALS_TO);
						}
					}
			));
	}

	@Test
	public void getUsers() throws Exception {
		mockMvc.perform(get("/users"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("users"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
			.andExpect(model().attribute("users",
					new AssertionMatcher<List<User>>() {
						@Override
						public void assertion(List<User> actual) throws AssertionError {
							UserTestData.assertMatch(actual, ADMIN, USER);
						}
					}
			));
	}
}