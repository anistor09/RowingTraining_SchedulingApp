package nl.tudelft.sem.template.example.domain.participant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

//    boolean isBetterCertificate(Certificate other) {
//        int value1=-1;
//        int value2=-1;
//        ArrayList<String> strings=new ArrayList<String>(List.of(new String[]{"C4", "4+", "8+"}));
//        for(int i=0;i<strings.size();i++){
//            if(strings.get(i).equals(other.certificateType))
//                value2=i;
//            else if(strings.get(i).equals(this.certificateType))
//                value1=i;
//        }
//        return value1>=value2;
//    }

}
