package ru.javawebinar.topjava.util;

import java.util.Arrays;
import java.util.List;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
    		new User(null, "userName1", "email1@mail.ru", "password1", Role.ROLE_ADMIN)
    		, new User(null, "userName2", "email2@mail.ru", "password2", Role.ROLE_USER)
    		, new User(null, "userName3", "email3@mail.ru", "password3", Role.ROLE_USER)
    		, new User(null, "userName4", "email4@mail.ru", "password4", Role.ROLE_USER)
    		);

    
//    public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay) {
//        return getFiltered(meals, caloriesPerDay, meal -> true);
//    }
//
//    public static List<MealTo> getFilteredTos(Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
//        return getFiltered(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
//    }
//
//    private static List<MealTo> getFiltered(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
//        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
//                .collect(
//                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
////                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
//                );
//        return meals.stream()
//                .filter(filter)
//                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
//                .collect(Collectors.toList());
//    }
//
//    private static MealTo createTo(Meal meal, boolean excess) {
//        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
//    }
    
}