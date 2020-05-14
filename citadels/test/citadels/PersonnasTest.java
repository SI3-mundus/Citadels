package citadels;

import citadels.Personnas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static citadels.Personnas.Personnage.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersonnasTest{
     Personnas person;

    @BeforeEach
    void setUp() {
         person = new Personnas();
    }

    @Test
     void Personnas(){
        assertAll(
             ()->assertEquals(8,person.personnas.size()),
             ()->assertTrue(Assassin.equals(person.personnas.get(0)))
             );
    }
    @Test
    void Personnage(){
        assertAll(
                ()->assertEquals(1,Assassin.getNumber()),
                ()->assertEquals(2,Voleur.getNumber()),
                ()->assertEquals(3,Magicien.getNumber()),
                ()->assertEquals(4,Roi.getNumber()),
                ()->assertEquals(5,Eveque.getNumber()),
                ()->assertEquals(6,Marchand.getNumber()),
                ()->assertEquals(7,Architecte.getNumber()),
                ()->assertEquals(8,Condottiere.getNumber()),
                ()->assertEquals("Assassin",Assassin.getName()),
                ()->assertEquals("Voleur",Voleur.getName()),
                ()->assertEquals("Magicien",Magicien.getName()),
                ()->assertEquals("Roi",Roi.getName()),
                ()->assertEquals("Eveque",Eveque.getName()),
                ()->assertEquals("Marchand",Marchand.getName()),
                ()->assertEquals("Architecte",Architecte.getName()),
                ()->assertEquals("Condottiere",Condottiere.getName())
        );
    }

    @Test
    void shuffle(){
            person.shuffle();
            assertFalse(Assassin.equals(person.personnas.get(0)));
    }

    @Test
    void TakeAway(){
        person.takeAway(2);
        assertAll(
        ()->assertEquals(6,person.personnas.size()),
        ()->assertTrue(Magicien.equals(person.personnas.get(0)))
        );
    }
}