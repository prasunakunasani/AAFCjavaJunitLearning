package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

    public static void main(String[] args) {
         //load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //retrieve the bean from the spring container. This will do the setter injection stuff BTS for us.
        CricketCoach theCoach =  context.getBean("myCricketCoach", CricketCoach.class);

        //call methods on the bean
        //if we run the app before this line is added, we should see the information messages written in constructor and setter method of CricketCoach
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune());

        //call our new methods to get the literal values
        System.out.println(theCoach.getEmailAddress());
        System.out.println(theCoach.getTeam());

        //close the context
        context.close();
    }

}
