package functionaltests;

public class EveryOther {
    // Given a String, return a new String made of every other
    // char starting with the first, so "Hello" yields "Hlo".
    //
    // everyOther("Hello") -> "Hlo"
    // everyOther("Hi") -> "H"
    // everyOther("Heeololeo") -> "Hello"
    public String everyOther(String str) {
        String result = "";
        for(int i = 0; i < str.length(); i+=2){
            result += str.substring(i, i+1);
        }
        return result;
    }

}
