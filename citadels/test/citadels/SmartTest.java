package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartTest {
    private static final Smart smart1= new Smart(Personnas.Personnage.Magicien);
    private static final Smart smart2= new Smart(Personnas.Personnage.Marchand);

    @Test
    void constructor() {
        assertAll(
                () -> assertNotNull(smart1),
                () -> assertNotNull(smart2)
        );
    }

    @Test
    void action(){
        smart1.countpoints();
        smart2.countpoints();
        assertTrue(smart1.points == 0);
    }


}
