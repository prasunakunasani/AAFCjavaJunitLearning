package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIwithXMLConfigApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach theCoach = context.getBean("myBoxingCoach", Coach.class);

        System.out.println(theCoach.getDailyFortune());

        context.close();
    }
}
