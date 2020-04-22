package citadels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Personnas{
    enum  Personnage {
        Assassin(1,"Assassin"),Voleur(2,"Voleur"),Magicien(3,"Magicien"),
        Roi(4,"Roi"),Eveque(5,"Eveque"),Marchand(6,"Marchand"),
        Architecte(7,"Architecte"),Condottiere(8,"Condottiere");
        private int number;
        private String name;

        Personnage(int number,String name) {
            this.name=name;
            this.number=number;
        }
        int getNumber(){return number;}
        String getName(){return name;}
    }

    List<Personnage> personnas=new ArrayList<>();
    Personnas(){
        for (Personnage person : Personnage.values()){
            personnas.add(person);
        }
    }

    void shuffle(){Collections.shuffle(personnas);}

}
