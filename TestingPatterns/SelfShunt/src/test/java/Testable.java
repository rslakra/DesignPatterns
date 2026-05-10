//Class Under Test
public class Testable{

    Collaborator collaborator;

    public Testable( Collaborator collaborator ){
        this.collaborator = collaborator;
    }

    // Method under test, invokes Collaborator.collaboratorMethod()
    public void testableMethod(){
        collaborator.collaboratorMethod();
    }
}


