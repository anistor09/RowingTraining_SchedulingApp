package nl.tudelft.sem.template.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
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

    public Certificate(String certificateType){
        this.certificateType=certificateType;
    }



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


    boolean isBetterCertificate(Certificate other) {
        Map<String,Integer> hm = Map.of("C4",1, "4+", 2,"8+",3);

            return hm.get(this.getCertificateType())>=hm.get(other.getCertificateType());

    }

}
