package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RobotTest {
    private static final Robot robot1= new Robot(Personnas.Personnage.Voleur);
    private static final Robot robot2= new Robot(Personnas.Personnage.Eveque);

    @Test
    void constructor() {
        assertAll(
                () -> assertNotNull(robot1),
                () -> assertNotNull(robot2)
        );
    }

}
