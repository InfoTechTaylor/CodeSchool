package functionaltests;

public class FirstHalf {
    // Given a String of even length, return the first half.
    // So the String "WooHoo" yields "Woo".
    //
    // firstHalf("WooHoo") -> "Woo"
    // firstHalf("HelloThere") -> "Hello"
    // firstHalf("abcdef") -> "abc"
    public String firstHalf(String str) {
        if(str.length() % 2 == 0){
            int halfSize = str.length() / 2;
            return str.substring(0,halfSize);
        } else {
            System.out.println("String is not of even length");
            return null;
        }
    }

}
