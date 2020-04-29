package citadels;

import java.util.List;
import java.util.Random;

public class Dumb extends Player{

    Dumb(Personnas.Personnage personage) {
        super(personage);
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers){
        if(quartiers.size()>0) {
            if (countquartier()<7) {
                for(Quartiers.Quartier m : quartierenmain){
                    if(argent>=m.price){
                        get1Quartiers(quartiers);
                        return;
                    }
                }
                addargent();
            }
        }else {addargent();}
    }
}
