package functionaltests;

public class TrimOne {
    // Given a String, return a version without the first and
    // last char, so "Hello" yields "ell". The String length will be at least 2.
    //
    // trimOne("Hello") -> "ell"
    // trimOne("java") -> "av"
    // trimOne("coding") -> "odin"
    public String trimOne(String str) {
        StringBuilder sb = new StringBuilder(str);
        int stringLength = sb.length() -1;
        sb.deleteCharAt(stringLength);
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
