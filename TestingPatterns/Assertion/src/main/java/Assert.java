import java.util.*;

public abstract class Assert {

    public Collection failure = new ArrayList(); // of type Failure
    public Collection condition = new ArrayList(); // of type Condition
    public abstract void assert();
    public abstract void fail();

} // end Assert



