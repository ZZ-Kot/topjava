package ru.javawebinar.topjava.service.jdbc;

import org.junit.Assume;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

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