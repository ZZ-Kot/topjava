package ru.javawebinar.topjava.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.Meal;

/*
 * 3: Изменить MealRepository и InMemoryMealRepository таким образом,
 * чтобы вся еда всех пользователей находилась в одном общем хранилище,
 * но при этом каждый конкретный авторизованный пользователь мог видеть
 * и редактировать только свою еду.
 * */
@Repository
public interface MealRepository {

	List<Meal> getAll(Integer userId);
	
	// null if not found
	Meal getOne(int id);

	// null if not found, when updated
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

}
