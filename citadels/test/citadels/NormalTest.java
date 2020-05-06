package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalTest {
    private static final Normal normal1= new Normal(Personnas.Personnage.Condottiere);
    private static final Normal normal2= new Normal(Personnas.Personnage.Architecte);

    @Test
    void constructor() {
        assertAll(
                () -> assertNotNull(normal1),
                () -> assertNotNull(normal2)
        );
    }

    @Test
    void action(){
        normal1.countpoints();
        normal2.countpoints();
        assertTrue(normal2.points == 0);
    }

}
