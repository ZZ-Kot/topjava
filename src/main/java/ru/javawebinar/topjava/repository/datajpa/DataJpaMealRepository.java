package ru.javawebinar.topjava.repository.datajpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

/*
 * 1: Имплементировать DataJpaMealRepository.
 * */
@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
    	meal.getUser().setId(userId);
    	
        return repository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.findByIdAndUser_Id(id, userId);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.findByDateTimeGreaterThanEqualOrDateTimeLessThanEqualAndUser_Id(startDate, endDate, userId);
    }
    
    @Override
    public List<Meal> getAll(int userId) {
    	return repository.findByUser_Id(userId);
    }
}
