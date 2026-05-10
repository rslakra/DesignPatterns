import java.util.*;

public class AssertEquals extends Assert{

    private ArrayList equalsConditions; 

    private ArrayList equalsFailures; 



    public EqualsCondition getEqualsConditionAt(int index) {        
        return (EqualsCondition)equalsConditions.get(index);
    } // end getEqualsCondition        

    public void addEqualsCondition(EqualsCondition condition) {        
        equalsConditions.add(condition);
    } // end setEqualsCondition        

    public Failure getEqualsFailureAt(int index) {        
        return (Failure)equalsFailures.get(index);
    } // end getEqualsFailure        

    public void addEqualsFailure(Failure failure) {        
        equalsFailures.add(failure);
    } // end setEqualsFailure        

public ArrayList getEqualsConditions() {
	return equalsConditions;
}

	public ArrayList getEqualsFailures() {
		return equalsFailures;
	}

public void setEqualsConditions(ArrayList equalsConditions) {
	this.equalsConditions = equalsConditions;
}

	public void setEqualsFailures(ArrayList equalsFailures) {
		this.equalsFailures = equalsFailures;
	}

	public void assert() {
		for(int i=0; i<getEqualsConditions().size();i++){
			EqualsCondition thisEqC = getEqualsConditionAt(i);
			if(thisEqC.evaluate()){
				System.out.println("\nEqualsCondition Evaluate test number " + (i+1) + " passed");
			}else{
				System.err.println("\nEqualsCondition Evaluate test number " + (i+1) + " failed");
				Failure thisFail = getEqualsFailureAt(i);
				thisFail.perform(); 
			}
		}
		
	}

	public void fail() {
		// TODO Auto-generated method stub
	}

} // end AssertEquals
