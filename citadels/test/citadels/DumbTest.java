package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
