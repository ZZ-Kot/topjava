package ru.javawebinar.topjava.repository.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.model.Meal;

@ContextConfiguration({
    "classpath:spring/spring-app.xml",
    "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class JdbcMealRepositoryTest {

//    private static final Logger log = getLogger("result");
	

	MealRepository mealRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository#JdbcMealRepository(
	 * 											org.springframework.jdbc.core.JdbcTemplate
	 * 											, org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate)}.
	 */
	@Test
	public final void testJdbcMealRepository() {
//		Meal meal = any(Meal.class);
//		JdbcMealRepository jdbcMealRepository = Mockito.mock(JdbcMealRepository.class);
		fail("Not yet implemented"); // TODO
//		assertEquals(jdbcMealRepository, any(JdbcMealRepository.class));
	}

	/**
	 * Test method for {@link ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository#save(
	 * 											ru.javawebinar.topjava.model.Meal, int)}.
	 */
	@Test
	public final void testSave() {
//		fail("Not yet implemented"); // TODO
//		JdbcMealRepository jdbcMealRepository = mock(JdbcMealRepository.class);
		Meal saveMeal = mock(Meal.class);
//		Integer id = mock(Integer.class);
//		
//		when(jdbcMealRepository.save(saveMeal, id)).thenReturn(saveMeal);
		
		assertEquals(saveMeal, saveMeal);
	}

	/**
	 * Test method for {@link ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository#delete(int, int)}.
	 */
	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository#get(int, int)}.
	 */
	@Test
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository#getAll(int)}.
	 */
	@Test
	public final void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository#getBetweenInclusive(
	 * 											java.time.LocalDate, java.time.LocalDate, int)}.
	 */
	@Test
	public final void testGetBetweenInclusive() {
		fail("Not yet implemented"); // TODO
	}

}
