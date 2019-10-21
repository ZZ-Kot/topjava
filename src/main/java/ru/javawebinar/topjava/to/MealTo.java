package ru.javawebinar.topjava.to;

import java.time.LocalDateTime;

/*
 * 2: сделать Meal extends AbstractBaseEntity,
 * MealTo перенести в пакет ru.javawebinar.topjava.to (transfer objects)
 * */
/*
 * 4.4: конвертацию в MealTo можно делать как в слое web, так и в service
 * (Mapping Entity->DTO: Controller or Service?)
 * */
public class MealTo {

	private final Integer id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;

    
    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    
    public Integer getId() {
        return id;
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

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
    
}