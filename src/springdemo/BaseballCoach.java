package springdemo;

public class BaseballCoach implements Coach {

    //define a private field for the dependency
    private FortuneService fortuneService;

    //define a constructor for dependency injection
    public BaseballCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService;
    }

    @Override //the method we are overriding from the Coach interface
    // Baseballcoach is compliant by implementing a given coach interface. So, can access baseball coach explicitly or generic fashion using the Coach interface.
    public String getDailyWorkout()
    {
        return "Spring 30 minutes on batting practice";
    }

    @Override
    public String getDailyFortune() {
        //use my fortuneService to give a fortune
        return fortuneService.getFortune(); //(the dependency is helping)
    }
}