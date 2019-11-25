package ru.javawebinar.topjava.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

/*
 * 1: Добавить тесты контроллеров:
 * 1.2 ResourceControllerTest для style.css (проверить status и ContentType)
 * */
class ResourceControllerTest extends AbstractControllerTest {

	@Test
	final void testStyleCss() throws Exception {
		mockMvc.perform(get("/resources/css/style.css"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/css"));
	}

}
