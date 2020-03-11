package ru.javawebinar.topjava.to;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class MealTo extends BaseTo {

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")	// 2020-03-10 10:00
    private LocalDateTime dateTime;

	@NotBlank
	@Size(max = 500, message = "not more than 500 symbols")
    private String description;

	@NotNull
	@Range(min = 0, max = 5000, message = "not more than 5000 calories")
    private Integer calories;

	private Boolean excess;
    
	@ConstructorProperties({"id", "dateTime", "description", "calories", "excess"})
    public MealTo(Integer id, LocalDateTime dateTime, String description, Integer calories, Boolean excess) {
		super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((excess == null) ? 0 : excess.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealTo other = (MealTo) obj;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (excess == null) {
			if (other.excess != null)
				return false;
		} else if (!excess.equals(other.excess))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MealTo [dateTime=" + dateTime + ", description=" + description + ", calories=" + calories + ", excess="
				+ excess + "]";
	}

}