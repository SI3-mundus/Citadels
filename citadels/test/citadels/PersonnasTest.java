package citadels;

import citadels.Personnas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static citadels.Personnas.Personnage.Assassin;
import static citadels.Personnas.Personnage.Magicien;
import static citadels.Personnas.Personnage.Roi;

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