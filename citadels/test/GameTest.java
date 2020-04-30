package citadels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static citadels.Personnas.Personnage;
import static citadels.Personnas.Personnage.Roi;
import static junit.framework.TestCase.assertEquals;

public class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        Game game = new Game();
    }

    @Test
    void isRoisvisible(){
       game.visiblePersonage.add(Roi);
       assertEquals(true,game.isRoisvisible());
    }
}
