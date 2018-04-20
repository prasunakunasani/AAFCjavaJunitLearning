package springdemo;

public class TrackCoach implements Coach {

    private FortuneService fortuneService;
//created only so myApp.java will work
    public TrackCoach()
    {

    }

    public TrackCoach(FortuneService myFortuneService) {
        this.fortuneService = myFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5K";
    }

    @Override
    public String getDailyFortune() {
        return "Just Do It: "+ fortuneService.getFortune();
    }
}
