package citadels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class  PlayerTest {
    private Dumb dumb1= new Dumb(Personnas.Personnage.Assassin);
    private Dumb dumb2= new Dumb(Personnas.Personnage.Roi);
    private Smart smart1= new Smart(Personnas.Personnage.Magicien);
    private Smart smart2= new Smart(Personnas.Personnage.Marchand);
    private static final Quartiers.Quartier taverne= Quartiers.Quartier.Taverne;
    private static final Quartiers.Quartier marche= Quartiers.Quartier.Marche;
    private static final Quartiers.Quartier caserne= Quartiers.Quartier.Caserne;
    private static final Quartiers.Quartier manufacture= Quartiers.Quartier.Manufacture;
    private static final Quartiers.Quartier prison = Quartiers.Quartier.Prison;
    private static final Quartiers.Quartier eglise = Quartiers.Quartier.Eglise;
    private static final Quartiers.Quartier palais = Quartiers.Quartier.Palais;
    private static final Quartiers.Quartier chateau = Quartiers.Quartier.Chateau;
    private static final Quartiers.Quartier cathedrale = Quartiers.Quartier.Cathedrale;
    private Quartiers q;
    List<Personnas.Personnage> list1=new ArrayList<>();





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
        assertTrue(dumb1.points == 0);
        assertTrue(smart1.currentpoints == 0);
        dumb1.quartierenmain.add(marche);
        assertTrue(dumb1.countPotentialPoints()==2);
    }
    @Test
    void addArgent(){
        assertTrue(dumb1.argent==2);
        dumb1.addargent();
        assertTrue(dumb1.argent==4);
    }
    @Test
    void countQuartier(){
        smart1.quartierconstruit.add(chateau);
        smart1.quartierenmain.add(prison);
        assertTrue(smart1.countquartier()==2);
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
        smart1.quartierenmain=q.get4quartiers();
        smart1.laboratoire_ability(smart1.quartierenmain,0);
        assertTrue(smart1.argent==3);
    }
    @Test
    void countPotentialPoints(){
        dumb1.addargent();
        dumb1.quartierenmain.add(eglise);
        assertTrue(dumb1.countPotentialPoints()==2);
    }
    @Test
    void construitQuartier(){
        smart1.quartierenmain.add(taverne);
        smart1.construitquartier();
        assertTrue(smart1.quartierconstruit.size()==1);
        smart2.quartierenmain.add(manufacture);
        smart2.construitquartier();
        assertTrue(smart2.quartierconstruit.size()==0);
    }
    @Test
    void avaliableQuartier(){
        smart2.quartierenmain.add(cathedrale);
        smart2.quartierenmain.add(palais);
        assertTrue(smart2.availablenNumberofquartiersenmain()==2);
        smart2.quartierconstruit.add(cathedrale);
        assertTrue(smart2.availablenNumberofquartiersenmain()==1);
    }
    @Test
    void dumbChoice(){
        list1.add(Personnas.Personnage.Eveque);
        list1.add(Personnas.Personnage.Architecte);
        dumb1.choisirSonPersonnage(list1);
        assertTrue(dumb1.personnage== Personnas.Personnage.Eveque);
        list1.add(Personnas.Personnage.Roi);
        dumb1.choisirSonPersonnage(list1);
        assertTrue(dumb1.personnage== Personnas.Personnage.Roi);
    }
    @Test
    void smartChoice1(){
        list1.add(Personnas.Personnage.Eveque);
        list1.add(Personnas.Personnage.Marchand);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Eveque);
        smart1.quartierconstruit.add(taverne);
        smart1.quartierconstruit.add(marche);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Marchand);
    }
    @Test
    void smartChoice2(){
        list1.add(Personnas.Personnage.Eveque);
        list1.add(Personnas.Personnage.Marchand);
        list1.add(Personnas.Personnage.Roi);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Eveque);
        smart1.quartierconstruit.add(palais);
        smart1.quartierconstruit.add(chateau);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Roi);
    }
    @Test
    void smartChoice3(){
        list1.add(Personnas.Personnage.Eveque);
        list1.add(Personnas.Personnage.Marchand);
        list1.add(Personnas.Personnage.Roi);
        list1.add(Personnas.Personnage.Condottiere);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Eveque);
        smart1.quartierconstruit.add(caserne);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Condottiere);
    }
    @Test
    void smartChoice4(){
        list1.add(Personnas.Personnage.Marchand);
        list1.add(Personnas.Personnage.Roi);
        list1.add(Personnas.Personnage.Eveque);
        list1.add(Personnas.Personnage.Condottiere);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Marchand);
        smart1.quartierconstruit.add(eglise);
        smart1.choisirSonPersonnage(list1);
        assertTrue(smart1.personnage== Personnas.Personnage.Eveque);
}

}
