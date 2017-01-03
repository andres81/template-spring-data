package eu.andreschepers.templatespringdata.service;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                eu.andreschepers.templatespringdata.configuration.ApplicationConfiguration.class,
        }
)
@PropertySource("classpath:application-test.properties")
public class UserServiceTest {

    @org.junit.Test
    public void add() throws Exception {
        assertTrue(true);
    }

    @org.junit.Test
    public void findAll() throws Exception {
        assertTrue(true);
    }

    @org.junit.Test
    public void update() throws Exception {
        assertTrue(true);
    }

}