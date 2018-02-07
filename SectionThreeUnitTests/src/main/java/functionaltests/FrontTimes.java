package functionaltests;

public class FrontTimes {

    // Given a String and a non-negative int n, we'll say that the
    // front of the String is the first 3 chars, or whatever is there
    // if the String is less than length 3. Return n copies of the front;
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
    public String frontTimes(String str, int n) {
//        throw new UnsupportedOperationException("Not implemented");
        String frontOfString;
        if(str.length() < 3){
            frontOfString = str.substring(0, str.length());
        }else {
            frontOfString = str.substring(0, 3);
        }
        String result = "";
        if(n > 0){
            for(int i=0; i < n; i++){
                result = result + frontOfString;
            }
            return result;
        } else {
            return "integer is not a positive number";
        }
    }
}
