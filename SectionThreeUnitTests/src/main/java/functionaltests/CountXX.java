package functionaltests;

public class CountXX {

    // Count the number of "xx" in the given String. We'll say
    // that overlapping is allowed, so "xxx" contains 2 "xx".
    //
    // countXX("abcxx") -> 1
    // countXX("xxx") -> 2
    // countXX("xxxx") -> 3
    public int countXX(String str) {
//        throw new UnsupportedOperationException("Not implemented");
        String searchString = "xx";
        int count = 0;
        for(int i = 0; i < str.length() -1; i++){
            String substring = str.substring(i, i+2);
            if(substring.equals(searchString)){
                count++;
            }
        }
        return count;
    }
}
