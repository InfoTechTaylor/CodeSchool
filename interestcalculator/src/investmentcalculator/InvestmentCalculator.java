package investmentcalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class InvestmentCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        int numOfYears;
        double interestRate, startingInvestment, runningBalance, interestEarned=0, yearlyInterestEarned=0, quarterlyRunningBalance=0;
        String startingInvestmentString, interestRateString, numOfYearsString;

        System.out.println("What amount do you want to invest? ");
        startingInvestmentString = scanner.nextLine();
        startingInvestment = Double.parseDouble(startingInvestmentString);
        runningBalance = startingInvestment;

        System.out.println("What is the interest rate? ");
        interestRateString = scanner.nextLine();
        interestRate = Double.parseDouble(interestRateString);

        System.out.println("How many years? ");
        numOfYearsString = scanner.nextLine();
        numOfYears = Integer.parseInt(numOfYearsString);

        quarterlyRunningBalance = runningBalance;

        for(int i=1; i <= numOfYears; i++){
            System.out.println("Current Year: " + i);
            System.out.println("Starting balance for year: " + formatter.format(runningBalance));


            for(int quarter=0; quarter < 4; quarter++){
                interestEarned = quarterlyRunningBalance *  (interestRate / 100);
                yearlyInterestEarned+= interestEarned;
                quarterlyRunningBalance += interestEarned;

            }

            System.out.println("Total interest earned for year: " + formatter.format(yearlyInterestEarned));
            runningBalance += yearlyInterestEarned;
            System.out.println("End of year principle: " + formatter.format(runningBalance));
            yearlyInterestEarned = 0;


        }




    }
}
