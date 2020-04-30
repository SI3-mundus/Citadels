package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
