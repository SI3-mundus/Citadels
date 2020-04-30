package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
