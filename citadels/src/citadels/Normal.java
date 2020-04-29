package citadels;

import java.util.List;
import java.util.Random;

public class Normal extends Player{

    Normal(Personnas.Personnage personage) {
        super(personage);
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers) {
        Random n = new Random();
        if (quartiers.size() > 0) {
            if (countquartier() < 7) {
                int s = n.nextInt(10);
                if (s < 6) {
                    get1Quartiers(quartiers);
                } else {
                    addargent();
                }
            } else {
                int s = n.nextInt(10);
                if (s < 5) {
                    addargent();
                } else {
                    get1Quartiers(quartiers);
                }
            }
        } else {
            addargent();
        }
    }
}
