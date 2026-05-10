public class EqualsCondition extends Condition{


	

    public boolean evaluate() {        
        if(getObj1() instanceof String  && getObj2() instanceof String ){
        	String string1= (String) getObj1();
			String string2= (String) getObj2();
        	if(string1.equalsIgnoreCase(string2)){
        		return true;
        	}else{
        		return false;
        	}
        }else if(getObj1() instanceof Boolean && getObj2() instanceof Boolean){
        	boolean bool1=((Boolean)getObj1()).booleanValue();
			boolean bool2=((Boolean)getObj2()).booleanValue();
        	if( bool1 == bool2){
        		return true;
        	}else{
        		return false;
        	}
        }else if(getObj1() instanceof Integer && getObj2() instanceof Integer){
        	int int1 = ((Integer)getObj1()).intValue();
        	int int2 = ((Integer)getObj2()).intValue(); 
        	if( int1 == int2){
        		return true;
        	}else{
        		return false;
        	}
        /**
         * TODO Implement more types here
         */
        }else{
        	if(getObj1().hashCode() == getObj2().hashCode()){
        		return true;
        	}else{
        		return false;
        	}
        }
    } // end evaluate        

} // end EqualsCondition