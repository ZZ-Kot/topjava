package ru.javawebinar.topjava.web.meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

/*
 * 2: Реализовать MealRestController и протестировать его через MealRestControllerTest
 * 2.1 следите, чтобы url в тестах совпадал с параметрами в методе контроллера.
 * 		Можно добавить логирование <logger name="org.springframework.web" level="debug"/> для проверки маршрутизации.
 * 2.2 в параметрах getBetween принимать LocalDateTime (конвертировать через
 * 		@DATETIMEFORMAT WITH JAVA 8 DATE-TIME API), а передавать в тестах в формате
 * 		ISO_LOCAL_DATE_TIME (например '2011-12-03T10:15:30'). Вызывать super.getBetween()
 * 		пока без проверки на null, используя toLocalDate()/toLocalTime() (см. Optional п.3)
 * */
@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
	
	static final String REST_URL = "/rest/meals";
	
	// SoapUI tested
	// Curl tested
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    // SoapUI tested
    // Curl tested
	@Override
    @GetMapping("/{id}")
	public Meal get(@PathVariable int id) {
		return super.get(id);
	}

	// SoapUI tested
	// Curl tested
	@Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		super.delete(id);
	}

	// SoapUI tested
	// Curl tested
	@Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Meal create(@RequestBody Meal meal) {
		return super.create(meal);
	}

	// SoapUI tested
	// Curl tested
	@Override
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@RequestBody Meal meal, @PathVariable int id) {
		super.update(meal, id);
	}

	// SoapUI tested
	// Curl tested
	@Override
	@GetMapping(value = "/by")
	public List<MealTo> getBetween(
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate
			, @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate
			, @RequestParam @DateTimeFormat(iso = ISO.TIME) LocalTime startTime
			, @RequestParam @DateTimeFormat(iso = ISO.TIME) LocalTime endTime) {

		return super.getBetween(startDate, endDate, startTime, endTime);
	}
    
}