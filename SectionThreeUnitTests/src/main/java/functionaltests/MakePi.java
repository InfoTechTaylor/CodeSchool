package functionaltests;

public class MakePi {
    // Return an int array length n containing the first n digits of pi.
    //
    // makePi(3) -> {3, 1, 4}

    public int[] makePi(int n) {
//        throw new UnsupportedOperationException("Not implemented");
        int[] array = new int[n];
        double pi = Math.PI;
        String piString = Double.toString(pi);
        String piCharString;
        int piInt;

        for(int i = 0; i < array.length +1; i++){
            piCharString =  piString.substring(i, i+1);
            if(i==0){
                piInt = Integer.parseInt(piCharString);
                array[i] = piInt;
            }
            else if (!piCharString.equals(".")) {
                piInt = Integer.parseInt(piCharString);
                array[i-1] = piInt;
            }
        }
        return array;

    }
}
