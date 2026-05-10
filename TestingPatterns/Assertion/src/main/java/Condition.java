
public abstract class Condition {

    private Object obj1;
    private Object obj2;
    
    
    public void setObj1(Object setMe){
    	obj1 = setMe;
    }
    
	public void setObj2(Object setMe){
		obj2 = setMe;
	}
	
	public Object getObj1(){
		return obj1;
	}
	
	public Object getObj2(){
		return obj2;
	}
    

    public abstract boolean evaluate();

} // end Condition



