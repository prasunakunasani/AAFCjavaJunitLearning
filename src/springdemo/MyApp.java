package springdemo;

public class MyApp {


    public static void main(String[] args) {
        //create the object
        //changing this - Coach theCoach = new BaseballCoach(); to:
        Coach theCoach = new TrackCoach();

        //use the object
        System.out.println(theCoach.getDailyWorkout());
    }
}
