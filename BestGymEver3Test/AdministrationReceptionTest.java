import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Det här är ett test för AdministrationReception Class.

class AdministrationReceptionTest {

    @Test
    void isCoustomerActive() {

        // Här så kollar vi om personerna stämmer med programmet
        Customer person1 = new Customer("Chamade Coriola", "8512021234", "2021-10-15");
        Customer person2 = new Customer("Diamanda Djedi", "7608021234", "2020-01-01");

        // Här skapar vi objekt för testet
        String memberFile = System.getProperty("user.dir") + "/Coustomers.txt";
        String visitFile = System.getProperty("user.dir") + "/visitsFile.txt";
        AdministrationReception ad = new AdministrationReception(memberFile, visitFile);


        assertTrue(ad.isCoustomerActive(person1));
        assertFalse(ad.isCoustomerActive(person2));


        // Där här är en annan variant av att Testa Personerna
        /*
        assert(ad.isCoustomerActive(person1));
        assert(!ad.isCoustomerActive(person2));
         */
    }
}