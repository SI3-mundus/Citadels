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
         Personnas person = new Personnas();
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
        assertAll(
        ()->assertEquals(6,person.takeAway(2).size()),
        ()->assertTrue(Magicien.equals(person.personnas.get(0)))
        );
        }
        }