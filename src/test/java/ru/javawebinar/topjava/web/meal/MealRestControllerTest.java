package ru.javawebinar.topjava.web.meal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.getAllResult;
import static ru.javawebinar.topjava.MealTestData.getBetweenResult;
import static ru.javawebinar.topjava.MealTestData.getResult;
import static ru.javawebinar.topjava.MealTestData.postRequest;
import static ru.javawebinar.topjava.MealTestData.postResult;
import static ru.javawebinar.topjava.MealTestData.putRequest;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import ru.javawebinar.topjava.web.AbstractControllerTest;

class MealRestControllerTest extends AbstractControllerTest {
	
	@Test
	final void testGetAll() throws Exception {
		mockMvc.perform(get("/rest/meals"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().string(getAllResult));
	}
	
	@Test
	final void testGet() throws Exception {
		mockMvc.perform(get("/rest/meals/100002"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(getResult));
	}

	@Test
	final void testDelete() throws Exception {
		mockMvc.perform(delete("/rest/meals/100002"))
		.andDo(print())
		.andExpect(status().isNoContent());
	}

	@Test
	final void testCreate() throws Exception {
		mockMvc.perform(post("/rest/meals").contentType(MediaType.APPLICATION_JSON).content(postRequest))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().string(postResult));
	}

	@Test
	final void testUpdate() throws Exception {
		mockMvc.perform(put("/rest/meals/100002").contentType(MediaType.APPLICATION_JSON).content(putRequest))
		.andDo(print())
		.andExpect(status().isNoContent());
	}

	@Test
	final void testGetBetween() throws Exception {
		mockMvc.perform(get("/rest/meals/by?startDate=2015-05-20&endDate=2015-05-31&startTime=00:00&endTime=10:59"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().string(getBetweenResult));
	}

}
