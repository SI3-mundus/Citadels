package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DumbTest {
    private static final Dumb dumb1= new Dumb(Personnas.Personnage.Assassin);
    private static final Dumb dumb2= new Dumb(Personnas.Personnage.Roi);

    @Test
    void constructor() {
        assertAll(
                () -> assertNotNull(dumb1),
                () -> assertNotNull(dumb2)
        );
    }

    @Test
    void action(){
        dumb1.countpoints();
        dumb2.countpoints();
        assertTrue(dumb1.points == 0);
    }



}
