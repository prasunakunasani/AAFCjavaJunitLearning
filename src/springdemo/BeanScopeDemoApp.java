package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

    public static void main(String[] args) {
        //load spring configuration file

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");

        //retrieve bean from sprint container
        Coach theCoach = context.getBean("myCoach",Coach.class);
        Coach alphaCoach = context.getBean("myCoach",Coach.class);

        //check if they are the same
        boolean result = (theCoach == alphaCoach);

        //print out the results
        System.out.println("\nPointing to the same object: "+ result);

        //print out the memory location
        System.out.println("\nMemory location for theCoach: "+ theCoach);
        System.out.println("\nMemory location for alphaCoach: "+ alphaCoach+"\n");

        //close the context
        context.close();
    }
}
