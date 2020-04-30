package citadels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static citadels.Personnas.Personnage.*;

public class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void isRoisvisible(){
       game.visiblePersonage.add(Roi);
       assertEquals(true,game.isRoisvisible());
    }
}
