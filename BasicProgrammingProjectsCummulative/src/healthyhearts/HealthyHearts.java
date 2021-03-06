package healthyhearts;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        Scanner newReader = new Scanner(System.in);

        System.out.println("What is your age? ");
        String ageString = newReader.nextLine();
        int age = Integer.parseInt(ageString);

        double maxHeartRate = 220 - age;
        double targetHeartRateMin = maxHeartRate * .5;
        double targetHeartRateMax = maxHeartRate * .85;

        System.out.println("Your maximum heart rate should be " + Math.round(maxHeartRate) + " beats per minute");
        System.out.println("Your target HR Zone is " + Math.round(targetHeartRateMin) + " - " + Math.round(targetHeartRateMax) + " beats per minute");
    }
}
