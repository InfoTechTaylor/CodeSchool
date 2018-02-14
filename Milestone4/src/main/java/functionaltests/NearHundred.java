package functionaltests;

public class NearHundred {
    // Given an int n, return true if it is within 10 of 100
    // or 200.
    // Hint: Check out the Math class for absolute value
    //
    // nearHundred(103) -> true
    // nearHundred(90) -> true
    // nearHundred(89) -> false
    public boolean nearHundred(int n) {
        if(Math.abs(100 - n) <= 10){
            return true;
        } else if (Math.abs(200 - n) <= 10){
            return true;
        } else {
            return false;
        }
    }
}
