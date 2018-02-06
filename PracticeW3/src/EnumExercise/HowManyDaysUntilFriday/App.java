package EnumExercise.HowManyDaysUntilFriday;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("What day of the week is it? ");

        DaysOfWeek dayOfWeek = DaysOfWeek.valueOf(inputReader.nextLine().toUpperCase());

        switch(dayOfWeek){
            case MONDAY:
                System.out.println("4 days until Friday");
                break;
            case TUESDAY:
                System.out.println("3 days until Friday");
                break;
            case WEDNESDAY:
                System.out.println("2 day until Friday");
                break;
            case THURSDAY:
                System.out.println("1 day until Friday");
                break;
            case FRIDAY:
                System.out.println("Today is Friday! ");
                break;
            case SATURDAY:
                System.out.println("6 days until Friday");
                break;
            case SUNDAY:
                System.out.println("5 days until Friday");
                break;
            default:
                System.out.println("Unknown day of week.");
        }
    }



}
