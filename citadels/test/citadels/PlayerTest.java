package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    private static final Dumb dumb1= new Dumb(Personnas.Personnage.Assassin);
    private static final Dumb dumb2= new Dumb(Personnas.Personnage.Roi);
    private static final Smart smart1= new Smart(Personnas.Personnage.Magicien);
    private static final Smart smart2= new Smart(Personnas.Personnage.Marchand);
    private Quartiers q;

    @Test
    void constructor() {
        assertAll(
                () -> assertNotNull(dumb1),
                () -> assertNotNull(dumb2),
                () -> assertNotNull(smart1),
                () -> assertNotNull(smart2)
        );
    }

    @Test
    void action(){
        dumb1.countpoints();
        dumb2.countpoints();
        smart1.countpoints();
        smart2.countpoints();
        assertTrue(dumb1.points == 0);
        assertTrue(smart1.points == 0);
    }
    @Test
    void addArgent(){
        assertTrue(dumb1.argent==2);
        dumb1.addargent();
        assertTrue(dumb1.argent==4);
    }
    @Test
    void countQuartier(){
        assertTrue(smart1.countquartier()==0);
    }
    @Test
    void get1Quartier(){
        q = new Quartiers();
        dumb2.get1Quartiers(q.get4quartiers());
        assertTrue(dumb2.quartierenmain.size()==1);
    }
    @Test
    void get2Quartiers(){
        q=new Quartiers();
        dumb2.get2Quartiers(q.get4quartiers());
        assertTrue(dumb2.quartierenmain.size()==2);
    }
    @Test
    void labAbility(){
        q=new Quartiers();
        dumb1.quartierenmain=q.get4quartiers();
        dumb1.laboratoire_ability(dumb1.quartierenmain,dumb1.lowestPriceCardIndex(dumb1.quartierenmain));
        assertTrue(dumb1.argent==3);
    }
    @Test
    void countPotentialPoints(){
        q=new Quartiers();
        dumb1.quartierenmain=q.get4quartiers();
        assertTrue(dumb1.countPotentialPoints()!=0);
    }
    @Test
    void construitQuartier(){
        q= new Quartiers();
        smart1.quartierenmain = q.get4quartiers();
        System.out.println(smart1.quartierenmain);
        smart1.construitquartier();
        smart1.addargent();
        smart1.construitquartier();
        System.out.println(smart1.quartierconstruit);
    }
    @Test
    void avaliableQuartier(){
        q = new Quartiers();
        smart2.quartierenmain = q.get4quartiers();
        assertTrue(smart2.availablenNumberofquartiersenmain()==4);
    }

}
