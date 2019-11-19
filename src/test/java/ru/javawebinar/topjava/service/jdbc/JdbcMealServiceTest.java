package ru.javawebinar.topjava.service.jdbc;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {
	
	@Autowired
	private Environment env;
	
	@Before
	public void check() {
		for (String profile : env.getActiveProfiles()) {
			Assume.assumeTrue(profile.equals("jdbc"));
			System.out.println("profile " + profile);
		}
	}
	
}