package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    void action(){
        robot1.countpoints();
        robot2.countpoints();
        assertTrue(robot1.points == 0);
    }

}
