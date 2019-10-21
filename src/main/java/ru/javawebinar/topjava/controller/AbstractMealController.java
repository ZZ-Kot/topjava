package ru.javawebinar.topjava.controller;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;

public abstract class AbstractMealController {
	
    protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
    private MealService service;
    

    public List<Meal> getAll(Integer userId) {
        log.info("getAll");
        return (List<Meal>) service.getAll(userId);
    }

    public Meal getOne(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal);
    }

}