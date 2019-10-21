package ru.javawebinar.topjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.comparator.Comparators;

import ru.javawebinar.topjava.controller.AdminRestController;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/*
 * 5: включить классы еды в контекст Spring (добавить аннотации) и
 * вызвать из SpringMain любой метод MealRestController (проверить
 * что Spring все корректно заинжектил)
 * */
public class SpringMain {
	
//    public static final List<Meal> MEALS = Arrays.asList(
//            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 0),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 0),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 0),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 1),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 1),
//            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 1)
//    );
//
//    private static Map<Integer, Meal> repositoryMeal = new HashMap<Integer, Meal>() {{
//    	put(0, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 0));
//    	put(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 0));
//    	put(2, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 0));
//    	put(3, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 1));
//    	put(4, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 1));
//    	put(5, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 1));
//    }};
//    
//    private static Map<Integer, User> repositoryUser = new HashMap<Integer, User>() {{
//    	put(0, new User(null, "cuserName1", "email1@mail.ru", "password1", Role.ROLE_ADMIN));
//    	put(1, new User(null, "duserName2", "email2@mail.ru", "password2", Role.ROLE_USER));
//    	put(2, new User(null, "auserName3", "email3@mail.ru", "password3", Role.ROLE_USER));
//    	put(3, new User(null, "buserName4", "email4@mail.ru", "password4", Role.ROLE_USER));
//    }};
	
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
            
//            MealService mealService = appCtx.getBean(MealService.class);
            
//            mealService.test();
//            System.out.println("get 10 before " + mealService.getAll(10));
//            System.out.println("create meal " + mealService.create(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 10)));
//            System.out.println("get 10 after " + mealService.getAll(10));
//            System.out.println("get 1 before " + mealService.get(1));
//            mealService.delete(1);
//            System.out.println("get 1 after " + mealService.get(1));

        }
    	
//    	outMeal().stream().forEach(System.out::println);
//    	outUser().stream().forEach(System.out::println);
    	
//    	InMemoryMealRepository inMemoryMealRepository = new InMemoryMealRepository();
//    	InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
    	
    	
//    	inMemoryMealRepository.getAll().forEach(System.out::println);
//    	System.out.println(inMemoryMealRepository.getAll(1));
//    	inMemoryMealRepository.save(new Meal(10, LocalDateTime.of(2150, Month.MAY, 30, 10, 0), "Завтраг", 50000, 2));
//    	System.out.println(inMemoryMealRepository.save(new Meal(1, LocalDateTime.of(2150, Month.MAY, 30, 10, 0), "Завтраг", 50000, 2)));
//    	System.out.println(inMemoryMealRepository.getOne(0));
//    	inMemoryMealRepository.getAll().forEach(System.out::println);
//    	System.out.println(inMemoryMealRepository.delete(0));
//    	inMemoryMealRepository.getAll().forEach(System.out::println);
    	
//    	inMemoryUserRepository.getAll().forEach(System.out::println);
//    	System.out.println(inMemoryUserRepository.getOne(1));
//    	System.out.println(inMemoryUserRepository.getByEmail("email10@mail.ru"));
//    	System.out.println(inMemoryUserRepository.save(new User(null, "zuserName1", "email10@mail.ru", "password1", Role.ROLE_ADMIN)));
//    	System.out.println(inMemoryUserRepository.save(new User(10, "duserName1z", "email1@mail.ru", "password1", Role.ROLE_ADMIN)));
//    	System.out.println(inMemoryUserRepository.delete(1));
//    	inMemoryUserRepository.getAll().forEach(System.out::println);
    	
    	
    }

//	private static List<Meal> outMeal() {
////    private static void out() {
//		return 
//				repositoryMeal.entrySet().stream()
////		.filter(entry -> entry.getKey() == 0)
//				.filter(entry -> entry.getValue().getDescription().equals("Завтрак"))
////				.forEach(System.out::println);
//		.map(entry -> entry.getValue())
//		.collect(Collectors.toList());
//	}
// 
//    public static List<User> outUser() {
//    	return repositoryUser.entrySet().stream()
////    			.filter(entry -> entry.getValue().getId() == userId)
//    			.map(entry -> entry.getValue())
//    			.sorted((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()))
//    			.collect(Collectors.toList());
//    }
	
}
