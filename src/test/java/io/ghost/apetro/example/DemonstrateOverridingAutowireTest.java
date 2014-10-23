package io.ghost.apetro.example;

import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * Demonstrates how @Autowired works when overridden by explicit dependency injection.
 */
public class DemonstrateOverridingAutowireTest {

    /**
     * This is the demonstration.
     */
    @Test
    public void testOverridingAutowire() {

        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final HairSplitter hairSplitter = (HairSplitter) context.getBean("hairSplitter");

        // Even though setKnife() is annotated as Autowired, only the explicit injection from the applicationContext.xml
        // file will fire, so the setter method will have been called only once.
        assertEquals(1, hairSplitter.getNumberOfTimesSetKnifeCalled());
    }

}
