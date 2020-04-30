package citadels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static citadels.Quartiers.Quartier.*;
import static org.junit.jupiter.api.Assertions.*;


class test_quartier {
    private Quartiers q;

    @BeforeEach
    void setUp() {
        q = new Quartiers();
    }
    @Test
    void init(){
        assertAll(
                ()-> assertEquals(0, Temple.ordinal()),
                ()-> assertEquals(1, Eglise.ordinal()),
                ()-> assertEquals(10, Palais.ordinal())
        );

    }
    @Test
    void testSize(){
        assertEquals(65, q.quartiers.size());
    }
    @Test
    void get4quartiers(){
        assertFalse( q.get4quartiers().isEmpty());
    }
    @Test
    void shuffle(){
        q.shuffle();
        assertNotSame(q.quartiers.get(0), Temple);
    }

}