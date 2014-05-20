package cz.peinlich.exam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This class just loads the application context. It is responsible for testing that the
 * application context is properly created in tests. The class was generated using
 * start.spring.io.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 5:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
