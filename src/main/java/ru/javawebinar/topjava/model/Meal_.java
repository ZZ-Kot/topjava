package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-02T08:49:03.214+0200")
@StaticMetamodel(Meal.class)
public class Meal_ extends AbstractBaseEntity_ {
	public static volatile SingularAttribute<Meal, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Meal, String> description;
	public static volatile SingularAttribute<Meal, Integer> calories;
	public static volatile SingularAttribute<Meal, User> user;
}
