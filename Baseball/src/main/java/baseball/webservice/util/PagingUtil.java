package baseball.webservice.util;

import java.util.ArrayList;
import java.util.List;

public class PagingUtil {

    // HELPER METHODS

    public static Integer calculatePageNumber(int limit, int offset) {
        return offset/limit + 1;
    }

    public static List<Integer> getPageNumbers(int startPage, Integer numberOfPages) {
        List<Integer> pages = new ArrayList<>();

        for (int i = startPage; i < numberOfPages + startPage; i++) {
            pages.add(i);
        }
        return pages;
    }

    // end helper methods
}
