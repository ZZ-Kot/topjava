package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * 1: Сделать из Meal Hibernate entity
 * */
@NamedQueries({
	@NamedQuery(name = Meal.UPDATE, query = "UPDATE"
												+ " Meal m"
										+ " SET"
												+ " m.calories = :calories"
												+ " AND m.description = :description"
												+ " AND m.dateTime = :date_time"
										+ " WHERE"
												+ " m.id = :id"
												+ " AND m.user.id = :user_id"),
	@NamedQuery(name = Meal.GET_ONE, query = "SELECT m FROM Meal m WHERE m.id=?1 AND m.user.id=?2"),
    @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id"),
    @NamedQuery(name = Meal.BETWEEN, query = "SELECT m FROM Meal m WHERE m.dateTime BETWEEN ?1 AND ?2 AND m.user.id=?3"),
    @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m ORDER BY m.id"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends AbstractBaseEntity {

	public static final String UPDATE = "Meal.update";
	public static final String GET_ONE = "Meal.getOne";
    public static final String DELETE = "Meal.delete";
    public static final String BETWEEN = "Meal.getBetween";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    
	@NotNull
	@Column(name = "date_time", nullable = false, unique = true, columnDefinition = "timestamp default now()")
	private LocalDateTime dateTime;

	@NotBlank
	@Size(min = 5, max = 100)
	@Column(name = "description", nullable = false)
    private String description;

	@NotNull
	@Column(name = "calories", nullable = false)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    
    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

}
