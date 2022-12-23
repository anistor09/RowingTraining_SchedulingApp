package nl.tudelft.sem.template.example.domain.participant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * certificate of the Participant.
 */
@EqualsAndHashCode
@Getter
@Setter
public class Certificate {
    final transient String certificateType;

    /**
     * Constructor method
     * @param certificateType string that contains the certificate
     */
    public Certificate(String certificateType){
        this.certificateType=certificateType;
    }


    /**
     * Method that verify if the certificate is valid or not
     * @return true in case it is valid, else false
     */
    boolean isValid() {
        ArrayList<String> strings=new ArrayList<String>(List.of(new String[]{"C4", "4+", "8+"}));
        if(strings.contains(certificateType))
            return true;
        return false;
    }
    @Override
    public String toString() {
        return certificateType;
    }

    /**
     * Method that compare if a certificate is better than another one
     * @param other a certificate to compare actual certificate to
     * @return true if it is better, lese if not
     */
    boolean isBetterCertificate(Certificate other) {
        Map<String,Integer> hm = Map.of("C4",1, "4+", 2,"8+",3);

        return hm.get(this.getCertificateType())>=hm.get(other.getCertificateType());

    }

}
