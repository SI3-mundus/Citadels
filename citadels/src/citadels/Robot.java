
package citadels;

import java.util.*;


public class Robot extends Player{
    int n =1;

    Robot(Personnas.Personnage personage) {
        super(personage);
    }

    @Override
    void action(List<Quartiers.Quartier> quartiers){
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