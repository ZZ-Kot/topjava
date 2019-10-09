package ru.javawebinar.topjava.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MealTo implements Serializable {
	
	private Long id;
	
    private LocalDateTime dateTime;

    private String description;

    private int calories;

//    private final Supplier<Boolean> excess;
//    private final AtomicBoolean excess;
    private boolean excess;

    public MealTo(Long id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

//    public Boolean getExcess() {
//        return excess.get();
//    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public boolean isExcess() {
		return excess;
	}

	public void setExcess(boolean excess) {
		this.excess = excess;
	}

	@Override
	public String toString() {
		return "MealTo [id=" + id + ", dateTime=" + dateTime + ", description=" + description + ", calories=" + calories
				+ ", excess=" + excess + "]";
	}

}