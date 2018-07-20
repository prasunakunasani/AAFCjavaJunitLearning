package springdemo;

public class BoxingCoach implements Coach {

    private FortuneService fortuneService;

    public BoxingCoach(FortuneService fortuneService) {
        System.out.println("BoxingCoach.java - In constructor");
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Do Jab drills with partner";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
