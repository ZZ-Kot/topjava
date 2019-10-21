	package ru.javawebinar.topjava.service;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

/*
 * 4: Реализовать слои приложения для функциональности "еда".
 * API контроллера должна удовлетворять все потребности демо
 * приложения и ничего лишнего (см. демо).
 * */
/*
 * Смотрите на реализацию слоя для user и делаете по аналогии!
 * Если там что-то непонятно, не надо исправлять или делать по своему.
 * Задавайте вопросы. Если действительно нужна правка- я сделаю и напишу всем.
 * */
/*
 * 4.3: если еда не принадлежит авторизированному пользователю или отсутствует,
 * в MealService бросать NotFoundException.
 * */
/*
 * 4.5: в MealService постараться сделать в каждом методе только одни запрос к MealRepository
 * */
/*
 * 4.6 еще раз: не надо в названиях методов повторять названия класса (Meal).
 * */
/*
 * 5: включить классы еды в контекст Spring (добавить аннотации) и
 * вызвать из SpringMain любой метод MealRestController (проверить
 * что Spring все корректно заинжектил)
 * */
@Service
public class MealService {

    private final MealRepository repository;

    
    @Autowired
	public MealService(MealRepository repository) {
		this.repository = repository;
	}
	
    
    /*
     * Test method.
     * Remove me.
     * */
    public void test() {
    	System.out.println("Its alive.");
    }
    
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getOne(id), id);
    }

    public List<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    public void update(Meal meal) throws NotFoundException {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

}
