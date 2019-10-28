package ru.javawebinar.topjava.repository.jpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;


/*
 * 2: Имплементировать и протестировать JpaMealRepository.
 * */
@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;
    
    @PersistenceContext
    private JpaUserRepository userRepsitory;

	
    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
        	
        	User u = userRepsitory.get(userId);
        	Meal m = new Meal();
        	
        	m.setCalories(meal.getCalories());
        	m.setDateTime(meal.getDateTime());
        	m.setDescription(meal.getDescription());
        	m.setUser(u);
        	
            em.persist(m);
        } else {
	        em.createNamedQuery(Meal.UPDATE, Meal.class)
        		.setParameter("id", meal.getId())
        		.setParameter("user_id", meal.getUser().getId())
        		.setParameter("calories", meal.getCalories())
        		.setParameter("description", meal.getDescription())
        		.setParameter("date_time", meal.getDateTime())
        		.executeUpdate();
        }
        
        return meal;
    }

    @Override
    public Meal get(int id, int userId) {
    	return em.createNamedQuery(Meal.GET_ONE, Meal.class)
    			.setParameter(1, id)
    			.setParameter(2, userId)
    			.getSingleResult();
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class)
                .setParameter(1, startDate)
                .setParameter(2, endDate)
                .setParameter(3, userId)
                .getResultList();
    }

    @Override
    public List<Meal> getAll(int userId) {
    	return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).getResultList();
    }

}