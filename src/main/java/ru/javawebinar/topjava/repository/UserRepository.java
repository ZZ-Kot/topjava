package ru.javawebinar.topjava.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.User;

@Repository
public interface UserRepository {

	// List<User> getAll();
	List<User> getAll();

	// null if not found
	User getOne(int id);
	
	// null if not found
	User getByEmail(String email);

	// null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

}