package ru.javawebinar.topjava.repository.datajpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ru.javawebinar.topjava.model.Meal;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
	boolean delete(int id, int userId);

	@Transactional
	List<Meal> findByUser_Id(int userId);

	@Transactional
	Meal findByIdAndUser_Id(int id, int userId);
	
	@Transactional
	List<Meal> findByDateTimeGreaterThanEqualOrDateTimeLessThanEqualAndUser_Id(
			LocalDate startDate
			, LocalDate endDate
			, int userId);

}
