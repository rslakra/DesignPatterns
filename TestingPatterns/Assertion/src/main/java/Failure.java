import java.util.List;

public class Failure {

    private List actions; 

    private String message; 

    public Assert assert; 


    public void perform() {        
        System.out.println(this.message);
        //implement custom functionality here by extending 
        //this class and overwriting this method.
    } // end perform        

	public List getActions() {
		return actions;
	}

	public Assert getAssert() {
		return assert;
	}

	public String getMessage() {
		return message;
	}

	public void setActions(List actions) {
		this.actions = actions;
	}

	public void setAssert(Assert assert) {
		this.assert = assert;
	}

	public void setMessage(String message) {
		this.message = message;
	}

} // end Failure



