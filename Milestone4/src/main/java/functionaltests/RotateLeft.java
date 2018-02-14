package functionaltests;

public class RotateLeft {
    // Given an array of ints, return an array with the elements
    // “rotated left" so {1, 2, 3} yields {2, 3, 1}.
    //
    // rotateLeft({1, 2, 3}) -> {2, 3, 1}
    // rotateLeft({5, 11, 9}) -> {11, 9, 5}
    // rotateLeft({7, 0, 0}) -> {0, 0, 7}
    public int[] rotateLeft(int[] numbers) {
        int[] numbersRotatedLeft = new int[numbers.length];
        int lastIndexInArray = numbers.length -1;

        for(int i=0; i < numbers.length; i++){
            if(i == 0){
                numbersRotatedLeft[lastIndexInArray] = numbers[i];
            } else {
                numbersRotatedLeft[i-1] = numbers[i];
            }
        }
        return numbersRotatedLeft;
    }

}
