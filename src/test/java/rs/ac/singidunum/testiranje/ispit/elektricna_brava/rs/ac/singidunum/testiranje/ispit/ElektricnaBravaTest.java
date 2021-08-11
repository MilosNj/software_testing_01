package rs.ac.singidunum.testiranje.ispit.elektricna_brava.rs.ac.singidunum.testiranje.ispit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElektricnaBravaTest {

    private ElektricnaBrava elektricnaBrava;
    private ElektricniKljuc dobarElektricniKljuc;
    private ElektricniKljuc losElektricniKljuc;

    @BeforeEach
    public void prepareResources() {
        this.elektricnaBrava = new ElektricnaBrava(0x48AF915801L);
        this.dobarElektricniKljuc = new ElektricniKljuc(0xB7506EA7FEL);
        this.losElektricniKljuc = new ElektricniKljuc(0x3389E9912FL);
    }

    @Test
    public void getBrojPokusajaOtkljucavanjaSaPogresnimKodom_ShouldReturn0_IfNoMethodsWereCalled() {
        int expected = 0;
        int actual = this.elektricnaBrava.getBrojPokusajaOtkljucavanjaSaPogresnimKodom();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getBrojPokusajaOtkljucavanjaSaPogresnimKodom_ShouldReturn1_If1UnlockAttemptWasMadeWithWrongKey() {
        int expected = 1;
        this.elektricnaBrava.otkljucaj(losElektricniKljuc);
        int actual = this.elektricnaBrava.getBrojPokusajaOtkljucavanjaSaPogresnimKodom();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getBrojPokusajaOtkljucavanjaSaPogresnimKodom_ShouldReturn2_If2UnlockAttemptsWereMadeWithWrongKey() {
        int expected = 2;
        this.elektricnaBrava.otkljucaj(losElektricniKljuc);
        this.elektricnaBrava.otkljucaj(losElektricniKljuc);
        int actual = this.elektricnaBrava.getBrojPokusajaOtkljucavanjaSaPogresnimKodom();
        Assertions.assertEquals(expected, actual);
    }

    // Kada ni jedan metod nije pozvan, ovaj metod treba da vrati true, a vraca false. Radi isto sto i jeZakljucana()
    @Test
    public void jeOtkljucana_ShouldReturnTrue_IfNoMethodsWereCalled() {
        Assertions.assertTrue(this.elektricnaBrava.jeOtkljucana());
    }

    // jeOtkljucana() uvek vraca suprotno od ocekivanog. Radi isto kao i jeZakljucana()
    @Test
    public void jeOtkljucana_ShouldReturnFalse_IfZakljucajWasCalled() {
        this.elektricnaBrava.zakljucaj(dobarElektricniKljuc);
        Assertions.assertFalse(this.elektricnaBrava.jeOtkljucana());
    }

    @Test
    public void jeZakljucana_ShouldReturnFalse_IfNoMethodsWereCalled() {
        Assertions.assertFalse(this.elektricnaBrava.jeZakljucana());
    }

    @Test
    public void jeZakljucana_ShouldReturnTrue_IfZakljucajWasCalled() {
        this.elektricnaBrava.zakljucaj(dobarElektricniKljuc);
        Assertions.assertTrue(this.elektricnaBrava.jeZakljucana());
    }


    @Test
    public void otkljucaj_ShouldUnlockTheLock_IfTheLockWasLockedWithTheGoodKey() {
        this.elektricnaBrava.zakljucaj(losElektricniKljuc);
        this.elektricnaBrava.otkljucaj(dobarElektricniKljuc);
        Assertions.assertTrue(true, String.valueOf(this.elektricnaBrava.jeZakljucana()));
    }

    @Test
    public void otkljucaj_ShouldNotUnlockTheLock_IfTheLockWasLockedWithTheBadKey() {
        this.elektricnaBrava.zakljucaj(dobarElektricniKljuc);
        this.elektricnaBrava.otkljucaj(losElektricniKljuc);
        Assertions.assertTrue(true, String.valueOf(this.elektricnaBrava.jeZakljucana()));
    }
}
