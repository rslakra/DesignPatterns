import com.health.Patient;
import com.health.Referral;
import com.health.Provider;
import com.health.prov.Physician;
import java.util.Date;

public class ObjectMother{

        public static Referral createValidReferral(){
            Referral referral = new Referral(); // create a referral
            referral.setReferredOn( new Date() ); // starting today
            referral.setReferralEnd( null ); // with no end date
            return referral;
        }


        public static Patient createParticipatingPatient(){
            // since patient would require a DB call to determine
            // participation, we'll create a specialied patient here
            return new Patient(){
                public boolean isParticipating(){
                    return true;
                }
            };
        }

        public static void attachPatientToReferral( Referral referral, Patient patient ){
            // simply set the patient for the referral
            referral.setPatient( patient );
        }

        public static Physician createValidProvider(){
            // create any provider - could easily be a MedicalGroup
            return new Physician();
        }

        public static void attachReferringProviderToReferral( Referral referral, Provider provider ){
            // attach the object
            referral.setReferredBy( provider );
        }

        public static void attachReferredProviderToReferral(Referral referral, Provider provider) {
            // attach the object
            referral.setReferredTo(provider);
        }

}
