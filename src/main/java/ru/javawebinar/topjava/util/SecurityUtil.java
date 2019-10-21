package ru.javawebinar.topjava.util;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

/*
 * 4.2: SecurityUtil может использоваться только на слое web
 * (см. реализацию ProfileRestController). MealService можно
 * тестировать без подмены логики авторизации, принимаем в методах
 * сервиса и репозитория параметр userId: id владельца еды.
 * */
public class SecurityUtil {

    public static int authUserId() {
        return 1;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
    
}