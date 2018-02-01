package springdemo;

public class BaseballCoach implements Coach {

    @Override //the method we are overriding from the Coach interface
    // Baseballcoach is compliant by implementing a given coach interface. So, can access baseball coach explicitly or generic fashion using the Coach interface.
    public String getDailyWorkout()
    {
        return "Spring 30 minutes on batting practice";
    }

}