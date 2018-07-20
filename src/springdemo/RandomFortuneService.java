package springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {
    @Override
    public String getFortune() {

                String[] randomFortunes = new String[3];

                randomFortunes[0] = "Today is your lucky day!";
                randomFortunes[1] = "Tomorrow is not your bad day!";
                randomFortunes[2] = "Writing this to fill the random Fortune quota!";

                Random rand = new Random();
                int n = rand.nextInt(randomFortunes.length);

                return randomFortunes[n];
            }
        }

/*Could've/Should've done:

public class RandomFortuneService implements FortuneService {

	// create an array of strings
	private String[] data = {
			"Beware of the wolf in sheep's clothing",
			"Diligence is the mother of good luck",
			"The journey is the reward"
	};

	// create a random number generator
	private Random myRandom = new Random();

	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = myRandom.nextInt(data.length);

		String theFortune = data[index];

		return theFortune;
	}

}

 */