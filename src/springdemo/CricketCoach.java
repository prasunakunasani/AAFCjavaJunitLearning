package springdemo;

public class CricketCoach implements Coach {

    private FortuneService fortuneService;

    //create a no-arg constructor cause' Spring will call this constructor when they make a reference to our bean
    public CricketCoach()
    {
        System.out.println("CriketCoach: inside no-arg constructor");
    }

    //create a setter method that's called by Spring for the actual dependency injection
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println("CricketCoach: inside setter method - setFortuneService");
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
