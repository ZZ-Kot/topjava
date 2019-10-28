package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-10-28T14:15:32.179+0300")
@StaticMetamodel(Meal.class)
public class Meal_ extends AbstractBaseEntity_ {
	public static volatile SingularAttribute<Meal, String> description;
	public static volatile SingularAttribute<Meal, Integer> calories;
	public static volatile SingularAttribute<Meal, User> user;
	public static volatile SingularAttribute<Meal, LocalDateTime> dateTime;
}
