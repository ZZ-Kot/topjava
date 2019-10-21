package ru.javawebinar.topjava.repository.inmemory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

/*
 * 3: Изменить MealRepository и InMemoryMealRepository таким образом,
 * чтобы вся еда всех пользователей находилась в одном общем хранилище,
 * но при этом каждый конкретный авторизованный пользователь мог видеть
 * и редактировать только свою еду.
 * */
/*
 * 3.2: если по запрошенному id еда отсутствует или чужая, возвращать null/false
 * (см. комментарии в UserRepository)
 * */
/*
 * 3.3: список еды возвращать отсортированный в обратном порядке по датам
 * */
/*
 * 3.4: атомарность операций не требуется (коллизии при одновременном изменении еды одного пользователя можно не учитывать)
 * */
@Repository
public class InMemoryMealRepository implements MealRepository {
	
	private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    
    {
        MealsUtil.MEALS.forEach(this::save);
    }

    /*
     * Test method.
     * Remove me.
     * */
    public List<Meal> getAll() {
    	log.info("meal getAll");
    	
    	List<Meal> meals = repository.entrySet().stream()
//    			.filter(entry -> entry.getValue().getUserId() == userId)
    			.map(entry -> entry.getValue())
    			.sorted((m1, m2) -> m2.getDate().compareTo(m1.getDate()))
    			.collect(Collectors.toList());
    	
    	return meals.isEmpty() ? null : meals;
    }

    @Override
    public List<Meal> getAll(Integer userId) {
    	log.info("meal getAll " + userId);
    	
    	List<Meal> meals = repository.entrySet().stream()
    			.filter(entry -> entry.getValue().getUserId() == userId)
    			.map(entry -> entry.getValue())
    			.sorted((m1, m2) -> m2.getDate().compareTo(m1.getDate()))
    			.collect(Collectors.toList());
    	
    	return meals.isEmpty() ? null : meals;
    }

    @Override
    public Meal getOne(int id) {
    	log.info("meal get {}", id);
    	
    	return repository.get(id);
    }
    
    @Override
    public Meal save(Meal meal) {
    	log.info("meal save {}", meal);
    	
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
    	log.info("meal delete {}", id);
    	
        return repository.remove(id) != null;
    }
    
}

