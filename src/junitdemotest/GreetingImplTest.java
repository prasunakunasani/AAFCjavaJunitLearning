package junitdemotest;

import junitdemo.Greeting;
import junitdemo.GreetingImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingImplTest {

    @Test
    void greetShouldReturnAValidOutput() {
        Greeting greeting = new GreetingImpl();
        String result = greeting.greet("Junit");

        //checking if the result is not null
        assertNotNull(result);

        //compare results that are coming back to the result we expect
        assertEquals("Hello Junit",result);
    }
}