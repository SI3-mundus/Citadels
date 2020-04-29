package citadels;

import java.util.List;
import java.util.Random;

public class Dumb extends Player{
    Dumb(Personnage.Personne personae) {
        super(personae);
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers){
        Random n=new Random();
        if(quartiers.size()>0) {
            if (countquartier()<7) {
                    get1Quartiers(quartiers);
                }
        }else {addargent();}
    }
}
