package springdemo;

public class KickboxingCoach implements Coach {


    @Override
    public String getDailyWorkout() {
        return "Punch bag 30 times";
    }

    @Override
    public String getDailyFortune() {
        return null;
    }
}
