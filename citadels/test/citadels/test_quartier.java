package citadels;

import citadels.Quartiers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static citadels.Quartiers.Quartier.*;
import static org.junit.jupiter.api.Assertions.*;


public class test_quartier {
    Quartiers q;

    @BeforeEach
    void setUp() {
        Quartiers q = new Quartiers();
    }
    @Test
    void init(){
        assertAll(
                ()->assertTrue(Temple.ordinal()==0),
                ()-> assertTrue(Eglise.ordinal()==1),
                ()-> assertTrue(Quartiers.Quartier.Palais.ordinal()==10)
        );

    }
    @Test
    void testSize(){
        Quartiers q = new Quartiers();
        assertTrue(q.quartiers.size()==65);
    }
//    @Test
//    void getfourquartiers(Quartiers q){
//        assertFalse( q.get4quartiers().isEmpty());
//    }
}