package citadels;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> players=new ArrayList<>();
    Quartiers quar=new Quartiers();
    Game(int playersnumber){
        Personnas persons=new Personnas();
        persons.shuffle();
        for (int i=0;i<playersnumber;i++){
            players.add(new Player(persons.personnas.get(i)));
        }
        quar.shuffle();
        for (Player player:players){
            player.quartierenmain=quar.get4quartiers();
        }
    }


}
