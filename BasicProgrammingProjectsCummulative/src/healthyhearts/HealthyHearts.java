package healthyhearts;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        Scanner newReader = new Scanner(System.in);

        System.out.println("What is your age? ");
        int age = newReader.nextInt();

        double maxHeartRate = 220 - age;
        double targetHeartRateMin = maxHeartRate * .5;
        double targetHeartRateMax = maxHeartRate * .85;

        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");
        System.out.println("Your target HR Zone is " + targetHeartRateMin + " - " + targetHeartRateMax + " beats per minute");
    }
}
