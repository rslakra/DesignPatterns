import java.util.*;

public class DaysUtility {

    // Number of milliseconds in a day
    static final long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
    
    public static long daysBetween( Date date1, Date date2 ){       
        long date1Milliseconds = date1.getTime(); // get date1 in millis
        long date2Milliseconds = date2.getTime(); // get date2 in millis
        // compute the difference and get the days.
        return (date1Milliseconds - date2Milliseconds) / MILLIS_PER_DAY;
    }
}
