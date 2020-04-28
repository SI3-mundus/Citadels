
package citadels;

import java.util.*;


public class Robot extends Player{

    Robot(Personnas.Personnage personae) {
        super(personae);
    }

    @Override
    void action(List<Quartiers.Quartier> quartiers){
        int n =1;
        if(quartiers.size()>0) {
            if (countquartier()<7) {
                if (n%2 == 0) {
                    get1Quartiers(quartiers);
                    n++;
                } else {
                    addargent();
                    n++;
                }
            }
        }else {addargent();}
    }
}