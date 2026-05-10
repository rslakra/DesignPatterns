import junit.framework.TestCase;

import com.health.Patient;
import com.health.Referral;

public class ReferralAdministratorTest extends TestCase{

    public void testPatientParticipation(){
        Referral referral = new Referral();
        Patient parPatient = new Patient();
        referral.setPatient( parPatient );
        
        assertTrue( ReferralAdministrator.isValid( referral ) );
    }
}
