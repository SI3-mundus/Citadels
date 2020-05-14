package citadels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static citadels.Personnas.Personnage.*;

public class GameTest {
    Game game;
    ArrayList<Player> playersdemo=new ArrayList<Player>();

    @BeforeEach
    void setUp() {
        game = new Game();

    }

    @Test
    void constructor(){
        assertEquals(4,game.playersnumber);
        assertEquals(4,game.players.get(0).quartierenmain.size());
        assertNotEquals(2,game.players.get(2).quartierenmain.size());
    }

    @Test
    void isRoisvisible(){
       game.visiblePersonage.add(Roi);
       assertEquals(true,game.isRoisvisible());
    }

    @Test
    void getPersonnagesPourChoisir(){
        game.getPersonnagesPourChoisir();
        assertNotNull(game.getPersonnagesPourChoisir());
        assertEquals(2,game.visiblePersonage.size());
        assertEquals(1,game.invisiblePersonage.size());
        assertTrue(!game.visiblePersonage.contains(Personnas.Personnage.Roi));
    }

    @Test
    void attribuerPersonnage(){
        assertNotNull(game.choisirPersonnage());
    }
    @Test
    void callPesonnage(){
        game.players.clear();
        game.players.add(new Dumb(Assassin));
        game.players.add(new Dumb(Voleur));
        game.players.add(new Dumb(Magicien));
        game.players.add(new Dumb(Roi));
        game.players.add(new Dumb(Eveque));
        game.players.add(new Dumb(Marchand));
        game.players.add(new Dumb(Architecte));
        game.players.add(new Dumb(Condottiere));
        
    }
}
