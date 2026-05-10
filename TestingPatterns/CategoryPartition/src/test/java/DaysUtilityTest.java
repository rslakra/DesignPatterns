import java.util.*;
import junit.framework.*;

public class DaysUtilityTest extends TestCase {
    
public static int DAY_RANGE_MAX = 50;

public void testDaysBetween() {

    // start a calendar at today
    Calendar cal = Calendar.getInstance() ;        
    
    // keep adding a day for the course of the range
    for( int daysDifference = 0; daysDifference <= DAY_RANGE_MAX; daysDifference++ ){
        cal.add(Calendar.DAY_OF_YEAR, 1 );
        // make sure we are at an ever increasing distance
        assertEquals( daysDifference, DaysUtility.daysBetween(cal.getTime(),new Date() ) );
    }        
}

public void testDaysBetween_London() {

    // start a calendar at today
    Calendar cal = Calendar.getInstance( TimeZone.getTimeZone("GMT" ) );        
    
    // keep adding a day for the course of the range
    for( int daysDifference = 0; daysDifference <= DAY_RANGE_MAX; daysDifference++ ){
        cal.add(Calendar.DAY_OF_YEAR, 1 );
        // make sure we are at an ever increasing distance
        assertEquals( daysDifference, DaysUtility.daysBetween(cal.getTime(),new Date() ) );
    }        
}

}
