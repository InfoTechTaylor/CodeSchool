package functionaltests;

public class StringSplosion {

    // Given a non-empty String like "Code" return a String like
    // “CCoCodCode".  (first char, first two, first 3, etc)
    //
    // stringSplosion("Code") -> "CCoCodCode"
    // stringSplosion("abc") -> "aababc"
    // stringSplosion("ab") -> "aab"
    public String stringSplosion(String str) {
        String stringToReturn = "";
        for(int i = 1; i <= str.length(); i++){
            stringToReturn += str.substring(0, i);
        }

        return stringToReturn;
    }
}
