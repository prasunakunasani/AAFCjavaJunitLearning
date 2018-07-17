package springdemo;

public class CricketCoach implements Coach {

    private FortuneService fortuneService;

    //add new fields for emailAddress and team
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        System.out.println("CricketCoach: inside setter method - setEmailAddress");
        this.emailAddress = emailAddress;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        System.out.println("CricketCoach: inside setter method - setTeam");
        this.team = team;
    }

    private String team;

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
