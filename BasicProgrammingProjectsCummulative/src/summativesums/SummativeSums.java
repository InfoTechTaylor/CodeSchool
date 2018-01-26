package summativesums;

public class SummativeSums {
    public static void main(String[] args) {
        int[] array1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] array2 = { 999, -60, -77, 14, 160, 301 };
        int[] array3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };

        int sumOfArray1 = getSum(array1);
        int sumOfArray2 = getSum(array2);
        int sumOfArray3 = getSum(array3);

        System.out.println("#1 Array sum: " + sumOfArray1);
        System.out.println("#2 Array sum: " + sumOfArray2);
        System.out.println("#3 Array sum: " + sumOfArray3);
    }

    public static int getSum(int[] arrayOfInts){
        int sum = 0;
        for(int i = 0; i < arrayOfInts.length; i++){
            sum+=arrayOfInts[i];
        }
        return sum;
    }
}
