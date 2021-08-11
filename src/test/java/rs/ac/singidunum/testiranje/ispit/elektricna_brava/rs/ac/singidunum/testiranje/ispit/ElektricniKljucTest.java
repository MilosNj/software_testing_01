package rs.ac.singidunum.testiranje.ispit.elektricna_brava.rs.ac.singidunum.testiranje.ispit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElektricniKljucTest {

    @Test
    public void getKodKljuca_ShouldReturnItsFiveByteKey() {
        long expected = 0x1122334455L;
        ElektricniKljuc elektricniKljuc = new ElektricniKljuc(expected);
        long actual = elektricniKljuc.getKodKljuca();
        Assertions.assertEquals(expected, actual);
    }
}
