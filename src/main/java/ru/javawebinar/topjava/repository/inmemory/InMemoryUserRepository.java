package ru.javawebinar.topjava.repository.inmemory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

/*
 * 1: Имплементировать InMemoryUserRepository по аналогии с InMemoryMealRepository
 * (список пользователей возвращать отсортированным по имени)
 * */
/*
 * 3.2: если по запрошенному id еда отсутствует или чужая, возвращать null/false
 * (см. комментарии в UserRepository)
 * */
@Repository
public class InMemoryUserRepository implements UserRepository {
	
	private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

	private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    
    {
        UsersUtil.USERS.forEach(this::save);
    }
    
    
    @Override
    public List<User> getAll() {
    	log.info("user getAll");
    	
    	return repository.entrySet().stream()
    			.map(entry -> entry.getValue())
    			.sorted((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()))
    			.collect(Collectors.toList());
    }
    
    @Override
    public User getOne(int id) {
    	log.info("user get {}", id);
    	
    	return repository.get(id);
    }
    
    @Override
    public User getByEmail(String email) {
    	log.info("user getByEmail {}", email);
    	
    	List<User> users = repository.entrySet().stream()
        		.filter(entry -> entry.getValue().getEmail().equals(email))
        		.map(entry -> entry.getValue())
        		.collect(Collectors.toList());
    	
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User save(User user) {
        log.info("user save {}", user);
        
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        // treat case: update, but not present in storage

        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
    	log.info("user delete {}", id);
    	
    	return repository.remove(id) != null;
    }
    
}
