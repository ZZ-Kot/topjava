package ru.javawebinar.topjava.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-17T10:29:46.263+0200")
@StaticMetamodel(User.class)
public class User_ extends AbstractNamedEntity_ {
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, Date> registered;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Integer> caloriesPerDay;
	public static volatile ListAttribute<User, Meal> meals;
}
