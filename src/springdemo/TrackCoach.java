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

    //add an init method - custom code
    public void doMyStartupStuff(){
        System.out.println("TrackCoach: inside method doMyStartUpStuff");
    }

    //add a destroy method
    public void doMyCleanupStuffYoYo(){
        System.out.println("TrackCoach: inside method doMyCleanupStuffYoYo");
    }


}

