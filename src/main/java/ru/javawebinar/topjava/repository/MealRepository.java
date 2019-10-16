package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/*
 * 3: Изменить MealRepository и InMemoryMealRepository таким образом,
 * чтобы вся еда всех пользователей находилась в одном общем хранилище,
 * но при этом каждый конкретный авторизованный пользователь мог видеть
 * и редактировать только свою еду.
 * */
public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    Collection<Meal> getAll(Integer id);
}
