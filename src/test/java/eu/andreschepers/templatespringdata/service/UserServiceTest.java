package eu.andreschepers.templatespringdata.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                eu.andreschepers.templatespringdata.configuration.ApplicationConfiguration.class,
                eu.andreschepers.templatespringdata.configuration.TestConfiguration.class
        }
)
@ActiveProfiles("test")
public class UserServiceTest {

    @org.junit.Test
    public void add() throws Exception {

    }

    @org.junit.Test
    public void findAll() throws Exception {

    }

    @org.junit.Test
    public void update() throws Exception {
        System.out.println(true);
        assertTrue(true);
    }

}