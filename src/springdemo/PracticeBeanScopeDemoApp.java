package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeBeanScopeDemoApp {
    public static void main(String[] args) {

        //load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("practice-applicationContext.xml");

        //load two beans
        Coach theCoach = context.getBean("myBoxingCoach",Coach.class);
        Coach alphaCoach = context.getBean("myBoxingCoach",Coach.class);

       //compare the beans in terms of memory
        System.out.println("theCoach location is: "+theCoach);
        System.out.println("alphaCoach location is: "+alphaCoach);

        //if the locations are dif, then beans are configured using prototype. If they are same, then the default Singleton scope was used.
    }
}
