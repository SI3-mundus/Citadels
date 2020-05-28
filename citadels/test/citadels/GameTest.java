package citadels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static citadels.Personnas.Personnage.*;
import static citadels.Quartiers.Quartier.*;


public class GameTest {
    Game game,game1,game2;

    Dumb Dumb4 = new Dumb(Roi);
    Dumb Dumb1 = new Dumb(Assassin);
    Dumb Dumb7 = new Dumb(Architecte);
    Dumb Dumb8 = new Dumb(Condottiere);
    Dumb Dumb2 = new Dumb(Voleur);
    Dumb Dumb3 = new Dumb(Magicien);
    Dumb Dumb5 = new Dumb(Eveque);
    Dumb Dumb6 = new Dumb(Marchand);

    List<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        game = new Game();
        game1 = new Game();
        game2 = new Game();

    }

    @Test
    void constructor(){
        assertEquals(4,game.playersnumber);
        for(int i = 0; i < 4; i++){
            assertEquals(4,game.players.get(i).quartierenmain.size());
            for (int j = 0; j < game.players.get(i).quartierenmain.size(); j++) {
                assertTrue(game.players.get(i).quartierenmain.get(j) instanceof Quartiers.Quartier);
            }
        }
    }

    @Test
    void isRoisvisible(){
       game.visiblePersonage.add(Roi);
        assertTrue(game.isRoisvisible());
    }


    @Test
    void getPersonnagesPourChoisir(){
        game.playersnumber = 4;
        game.getPersonnagesPourChoisir();
        assertNotNull(game.getPersonnagesPourChoisir());
        assertEquals(2,game.visiblePersonage.size());
        assertEquals(1,game.invisiblePersonage.size());
        assertFalse(game.visiblePersonage.contains(Roi));
        assertEquals(5,game.ListeDePersonagePourChoisir.size());
        for (int j = 0; j < game.ListeDePersonagePourChoisir.size(); j++) {
            assertTrue(game.ListeDePersonagePourChoisir.get(j) instanceof Personnas.Personnage);
        }


        game1.playersnumber = 5;
        game1.getPersonnagesPourChoisir();
        assertNotNull(game1.getPersonnagesPourChoisir());
        assertEquals(1,game1.visiblePersonage.size());
        assertEquals(1,game1.invisiblePersonage.size());
        assertFalse(game1.visiblePersonage.contains(Roi));
        assertEquals(6,game1.ListeDePersonagePourChoisir.size());
        for (int j = 0; j < game1.ListeDePersonagePourChoisir.size(); j++) {
            assertTrue(game1.ListeDePersonagePourChoisir.get(j) instanceof Personnas.Personnage);
        }


        game2.playersnumber = 6;
        game2.getPersonnagesPourChoisir();
        assertNotNull(game2.getPersonnagesPourChoisir());
        assertEquals(0,game2.visiblePersonage.size());
        assertEquals(1,game2.invisiblePersonage.size());
        assertFalse(game2.visiblePersonage.contains(Roi));
        assertEquals(7,game2.ListeDePersonagePourChoisir.size());
        for (int j = 0; j < game2.ListeDePersonagePourChoisir.size(); j++) {
            assertTrue(game2.ListeDePersonagePourChoisir.get(j) instanceof Personnas.Personnage);
        }
    }


    @Test
    void deciderquiRoi(){
        players.clear();
        players.add(Dumb1);
        players.add(Dumb2);
        players.add(Dumb3);
        players.add(Dumb4);
        game1.players = players;
        game1.deciderquiRoi();
        assertTrue(game1.players.get(3).isRoilasttour);
        assertFalse(game1.players.get(0).isRoilasttour);
        assertFalse(game1.players.get(1).isRoilasttour);
        assertFalse(game1.players.get(2).isRoilasttour);
    }

    @Test
    void choisirPersonnage(){
        players.clear();
        players.add(Dumb1);
        players.add(Dumb2);
        players.add(Dumb3);
        players.add(Dumb4);
        game1.players = players;
        game1.choisirPersonnage();
        Set<Personnas.Personnage> s = new HashSet<>();
        for(Player p : game1.players){
            assertTrue(p.personnage instanceof Personnas.Personnage);
            s.add(p.personnage);
        }
        assertEquals(4,s.size());

    }

    @Test
    void getOtherPlayers(){
        players.clear();
        players.add(Dumb1);
        players.add(Dumb2);
        players.add(Dumb3);
        players.add(Dumb4);
        game1.players = players;
        assertEquals(game1.getOtherPlayers(game1.players.get(0)),players.subList(1,4));
        assertEquals(game1.getOtherPlayers(game1.players.get(3)),players.subList(0,3));
        List<Player> ps = new ArrayList<>();
        ps.add(Dumb1);
        ps.add(Dumb3);
        ps.add(Dumb4);
        assertEquals(game1.getOtherPlayers(game1.players.get(1)),ps);
        ps.clear();
        ps.add(Dumb1);
        ps.add(Dumb2);
        ps.add(Dumb4);
        assertEquals(game1.getOtherPlayers(game1.players.get(2)),ps);

    }

    @Test
    void oneTour(){

    }

    @Test
    void getWinnerPosition(){
        players.clear();
        players.add(Dumb1);
        players.add(Dumb2);
        players.add(Dumb3);
        players.add(Dumb4);
        game1.players = players;
        game1.players.get(0).quartierconstruit.add(Monastere);
        game1.players.get(0).quartierconstruit.add(Temple);
        game1.players.get(0).quartierconstruit.add(Tourdeguet);
        game1.players.get(0).quartierconstruit.add(Manoir);
        game1.players.get(0).quartierconstruit.add(Palais);
        game1.players.get(0).quartierconstruit.add(Taverne);
        game1.players.get(0).quartierconstruit.add(Marche);

        game1.players.get(1).quartierconstruit.add(Manoir);
        game1.players.get(1).quartierconstruit.add(Palais);
        game1.players.get(1).quartierconstruit.add(Taverne);
        game1.players.get(1).quartierconstruit.add(Marche);

        game1.players.get(2).quartierconstruit.add(Temple);
        game1.players.get(2).quartierconstruit.add(Tourdeguet);
        game1.players.get(2).quartierconstruit.add(Manoir);
        game1.players.get(2).quartierconstruit.add(Courdesmiracles);
        game1.players.get(2).quartierconstruit.add(Taverne);
        game1.players.get(2).quartierconstruit.add(Marche);


        game1.players.get(3).quartierconstruit.add(Tourdeguet);
        game1.players.get(3).quartierconstruit.add(Manoir);
        game1.players.get(3).quartierconstruit.add(Palais);
        game1.players.get(3).quartierconstruit.add(Taverne);
        game1.players.get(3).quartierconstruit.add(Marche);
        game1.players.get(3).quartierconstruit.add(Laboratoire);
        game1.players.get(3).quartierconstruit.add(Dracoport);

        assertEquals(3,game1.getWinnerPosition());
    }
}
