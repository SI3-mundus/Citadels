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
    appelPersonages(){
        int i=0;
        int k=0;
        while i<9{
            i++;
            switch(i){
                case '1':
                    for (Player player:players){
                        if player.getNumber == 1{
                            k=player.action_assassin();
                        }
                    }
                    break;
                case'2':
                    for (Player player:players){
                        if player.getNumber == 2 && player.getNumber != k{
                            k=player.action_voleur();
                        }
                    }
                    break;
                case'3':
                    for (Player player:players){
                        if player.getNumber == 3 && player.getNumber != k{
                            k=player.action_magicien();
                        }
                    }
                    break;
                case'4':
                    for (Player player:players){
                        if player.getNumber == 4 && player.getNumber != k{
                            k=player.action_roi();
                        }
                    }
                    break;
                case '5':
                    for (Player player:players){
                        if player.getNumber == 5 && player.getNumber != k{
                            k=player.action_bishop();
                        }
                    }
                    break;
                case'6':
                    for (Player player:players){
                        if player.getNumber == 6 && player.getNumber != k{
                            k=player.action_marchant();
                        }
                    }
                    break;
                case'7':
                    for (Player player:players){
                        if player.getNumber == 7 && player.getNumber != k{
                            k=player.action_architect();
                        }
                    }
                    break;
                case'8':
                    for (Player player:players){
                        if player.getNumber == 8 && player.getNumber != k{
                            k=player.action_warlord();
                        }
                    }
                    break;

            }


        }

    }

    String play(){
        boolean finish=false;
        while (finish==false){
            for(Player player:players){
                player.action(quar.quartiers);
                player.construitquartier();
            }
            for (Player player:players){
                if (player.quartierconstruit.size()==7){
                    finish=true;
                    break;
                }
            }
        }
        for (Player player:players){
            player.countpoints();
        }
        for(int i=0;i<players.size();i++){
            if (players.get(i).quartierconstruit.size()==7){
                players.get(i).points=players.get(i).points+4;
                for(int m=i;m<players.size();m++){
                    if (players.get(m).quartierconstruit.size()==7){
                        players.get(m).points=players.get(m).points+2;
                    }
                }
                break;
            }
        }

        Player Winner=players.get(0).getWinner(players);
        return Winner.personnage.getName();
    }

    public static void main(String...args){
        Game game=new Game(4);
        game.play();
        System.out.println(game.play());
    }
}
