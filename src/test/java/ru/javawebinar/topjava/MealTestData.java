package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
	
	public static final String getAllResult = "[{\"id\":100008,\"dateTime\":\"2015-05-31T20:00:00\",\"description\":\"Ужин\",\"calories\":510,\"excess\":true}" + 
			",{\"id\":100007,\"dateTime\":\"2015-05-31T13:00:00\",\"description\":\"Обед\",\"calories\":1000,\"excess\":true}" + 
			",{\"id\":100006,\"dateTime\":\"2015-05-31T10:00:00\",\"description\":\"Завтрак\",\"calories\":500,\"excess\":true}" + 
			",{\"id\":100005,\"dateTime\":\"2015-05-31T00:00:00\",\"description\":\"Еда на граничное значение\",\"calories\":100,\"excess\":true}" + 
			",{\"id\":100004,\"dateTime\":\"2015-05-30T20:00:00\",\"description\":\"Ужин\",\"calories\":500,\"excess\":false}" + 
			",{\"id\":100003,\"dateTime\":\"2015-05-30T13:00:00\",\"description\":\"Обед\",\"calories\":1000,\"excess\":false}" + 
			",{\"id\":100002,\"dateTime\":\"2015-05-30T10:00:00\",\"description\":\"Завтрак\",\"calories\":500,\"excess\":false}]";
	public static final String getResult = "{\"id\":100002,\"dateTime\":\"2015-05-30T10:00:00\",\"description\":\"Завтрак\",\"calories\":500,\"user\":null}";
	public static final String postRequest = "{\"dateTime\": \"2019-05-30T10:00\", \"description\": \"Zautrag\", \"calories\": 200}";
	public static final String postResult = "{\"id\":100011,\"dateTime\":\"2019-05-30T10:00:00\",\"description\":\"Zautrag\",\"calories\":200,\"user\":null}";
	public static final String putRequest = "{\"dateTime\": \"2019-05-30T10:00\", \"description\": \"Zautrag\", \"calories\": 2000}";
	public static final String getBetweenResult = "[{\"id\":100006,\"dateTime\":\"2015-05-31T10:00:00\",\"description\":\"Завтрак\",\"calories\":500,\"excess\":true}"
			+ ",{\"id\":100005,\"dateTime\":\"2015-05-31T00:00:00\",\"description\":\"Еда на граничное значение\",\"calories\":100,\"excess\":true}"
			+ ",{\"id\":100002,\"dateTime\":\"2015-05-30T10:00:00\",\"description\":\"Завтрак\",\"calories\":500,\"excess\":false}]";
	
    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 9;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2015, Month.MAY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL_ID, of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL_ID + 1, of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final MealTo MEAL_TO1 = new MealTo(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, false);
    public static final MealTo MEAL_TO2 = new MealTo(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, false);
    public static final MealTo MEAL_TO3 = new MealTo(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, false);
    public static final MealTo MEAL_TO4 = new MealTo(MEAL1_ID + 3, of(2015, Month.MAY, 31, 0, 0), "Еда на граничное значение", 100, true);
    public static final MealTo MEAL_TO5 = new MealTo(MEAL1_ID + 4, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500, true);
    public static final MealTo MEAL_TO6 = new MealTo(MEAL1_ID + 5, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000, true);
    public static final MealTo MEAL_TO7 = new MealTo(MEAL1_ID + 6, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, true);
    
    public static final List<Meal> MEALS = List.of(MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    public static final List<MealTo> MEALS_TO = List.of(MEAL_TO7, MEAL_TO6, MEAL_TO5, MEAL_TO4, MEAL_TO3, MEAL_TO2, MEAL_TO1);

    public static Meal getNew() {
        return new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
    
    public static void assertMatchMealTo(Iterable<MealTo> actual, Iterable<MealTo> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

}
