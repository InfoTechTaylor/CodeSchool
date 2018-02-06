package investmentcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

public class InvestmentCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        int numOfYears;
        BigDecimal startingInvestment, runningBalance,
                interestEarned,
                yearlyInterestEarned = new BigDecimal("0"), quarterlyRunningBalance,
                interestRate;

        String startingInvestmentString, interestRateString, numOfYearsString;

        System.out.println("What amount do you want to invest? ");
        startingInvestmentString = scanner.nextLine();
        startingInvestment = new BigDecimal(startingInvestmentString);
        runningBalance = startingInvestment;

        System.out.println("What is the interest rate? ");
        interestRateString = scanner.nextLine();
        interestRate = new BigDecimal(interestRateString);


        System.out.println("How many years? ");
        numOfYearsString = scanner.nextLine();
        numOfYears = Integer.parseInt(numOfYearsString);

        quarterlyRunningBalance = runningBalance;

        BigDecimal oneHundred = new BigDecimal("100");
        for(int i=1; i <= numOfYears; i++){
            System.out.println("Current Year: " + i);
            System.out.println("Starting balance for year: " + formatter.format(runningBalance));


            for(int quarter=0; quarter < 4; quarter++){
                interestEarned = quarterlyRunningBalance.multiply(interestRate.divide(oneHundred, 2, RoundingMode.HALF_UP));
                yearlyInterestEarned = yearlyInterestEarned.add(interestEarned);
                quarterlyRunningBalance =  quarterlyRunningBalance.add(interestEarned);

            }

            System.out.println("Total interest earned for year: " + formatter.format(yearlyInterestEarned));
            runningBalance = runningBalance.add(yearlyInterestEarned);
            System.out.println("End of year principle: " + formatter.format(runningBalance));
            yearlyInterestEarned = new BigDecimal(0);


        }




    }
}
