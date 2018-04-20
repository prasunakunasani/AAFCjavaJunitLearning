package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

    public static void main(String[] args) {
        //Load the spring configuration file
        //--Step 2 - Create a Spring Container
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //Retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);

        //call methods on the bean
        System.out.println(theCoach.getDailyWorkout());

        //call methods for fortunes from dependency
        System.out.println(theCoach.getDailyFortune());

        //close the application context
        context.close();
    }
}
