package citadels;

import java.util.List;
import java.util.Random;

public class Dumb extends Player{
    Dumb(Personnas.Personnage personae) {
        super(personae);
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers){
        Random n=new Random();
        if(quartiers.size()>0) {
            if (countquartier()<4) {
                    get1Quartiers(quartiers);
                }
        }else {addargent();}
    }
}
