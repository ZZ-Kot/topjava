package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/*
* Реализовать метод UserMealsUtil.getFilteredWithExceeded через циклы (`forEach`):
* -  должны возвращаться только записи между startTime и endTime
* -  поле UserMealWithExceed.exceed должно показывать,
*                                      превышает ли сумма калорий за весь день параметра метода caloriesPerDay
*
* Т.е UserMealWithExceed - это запись одной еды, но поле exceeded будет одинаково для всех записей за этот день.
*
* - Проверьте результат выполнения ДЗ (можно проверить логику в http://topjava.herokuapp.com , список еды)
* - Оцените Time complexity алгоритма. Если она больше O(N), например O(N*N) или N*log(N), сделайте O(N).
* */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

//        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000));
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList
                                                                    , LocalTime startTime
                                                                    , LocalTime endTime
                                                                    , int caloriesPerDay) {
        final Map<LocalDate, Integer> sumOfCaloriesPerDay = mealList.stream()
                .collect(groupingBy(m -> m.getDateTime().toLocalDate() , summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .map(m -> new UserMealWithExceed(
                        m.getDateTime()
                        , m.getDescription()
                        , m.getCalories()
                        , sumOfCaloriesPerDay.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}
