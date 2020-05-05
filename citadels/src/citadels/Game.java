package citadels;

import java.util.*;


public class Game {
    List<Player> players=new ArrayList<>();
    Quartiers quar=new Quartiers();
    int round=1;
    boolean finish=false;
    List<Quartiers.Quartier> giveUpQuartiers=new ArrayList<>();
    List<Personnas.Personnage> ListeDePersonagePourChoisir=new ArrayList<>();
    List<Personnas.Personnage> visiblePersonage=new ArrayList<>();
    List<Personnas.Personnage> invisiblePersonage=new ArrayList<>();
    List<Personnas.Personnage> chooseList=new ArrayList<>();
    int playersnumber;

    Game(){
        this.playersnumber=4;
        getPersonnagesPourChoisir();
        players.add(new Dumb(ListeDePersonagePourChoisir.get(0)));
        players.add(new Robot(ListeDePersonagePourChoisir.get(1)));
        players.add(new Normal(ListeDePersonagePourChoisir.get(2)));
        players.add(new Smart(ListeDePersonagePourChoisir.get(3)));
        quar.shuffle();
        for (Player player:players){
            player.quartierenmain=quar.get4quartiers();
        }
    }

    boolean isRoisvisible(){
        boolean visible=false;
        for(int i=0;i<visiblePersonage.size();i++){
            if(visiblePersonage.get(i)== Personnas.Personnage.Roi){
                visible=true;
                break;
            }
        }
        return visible;
    }

    List<Personnas.Personnage> getPersonnagesPourChoisir(){
        Personnas personas=new Personnas();
        personas.shuffle();
        switch (playersnumber){
            case 4:
                visiblePersonage=personas.takeAway(2);
                invisiblePersonage=personas.takeAway(1);
                ListeDePersonagePourChoisir=personas.personnas;
                if(isRoisvisible()==true){getPersonnagesPourChoisir();}
                addChooseList();
                break;
            case 5:
                visiblePersonage=personas.takeAway(1);
                invisiblePersonage=personas.takeAway(1);
                ListeDePersonagePourChoisir=personas.personnas;
                if(isRoisvisible()==true){getPersonnagesPourChoisir();}
                addChooseList();
                break;
            case 6:
                visiblePersonage=personas.takeAway(0);
                invisiblePersonage=personas.takeAway(1);
                ListeDePersonagePourChoisir=personas.personnas;
                if(isRoisvisible()==true){getPersonnagesPourChoisir();}
                addChooseList();
                break;
        }
        return ListeDePersonagePourChoisir;
    }

    List<Player> attribuerPersonnage(){
        if(players.size()<ListeDePersonagePourChoisir.size()){
            for (int i=0;i<players.size();i++){
                players.get(i).personnage=ListeDePersonagePourChoisir.get(i);
            }
            return players;
        }else {return null;}
    }



    void oneTour(){
        for (int i=1;i<9;i++) {
            switch (i) {
                case 1:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 1) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_assassin(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 2:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 2 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_voleur(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 3:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 3 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_magicien(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 4:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 4 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_roi(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 5:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 5 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_eveque(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 6:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 6 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_marchant(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 7:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 7 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_architect(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                case 8:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 8 && player.isKilled != true) {
                            for (int n=0;n<player.quartierconstruit.size();n++){
                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
                                    player.quartierenmain.remove(0);
                                    player.argent ++;
                                }
                            }
                            player.action_condottiere(chooseList,players,quar.quartiers);
                            break;
                        }
                    }
                    break;
                default:
                    System.exit(2);
            }
        }
    }


    String play(){
        if(round==1){
            System.out.println("Tour "+round+" start");
            for (Player player:players){
                System.out.println(player.personnage);
            }
            oneTour();
            for (Player player:players){
                System.out.println(player.quartierconstruit.size());
            }
            System.out.println("Tour "+round+" finish");
        }
        while (!finish){
            round=round+1;
            System.out.println("Tour "+round+" start");
            getPersonnagesPourChoisir();
            attribuerPersonnage();
            for (Player player:players){
                System.out.println(player.personnage);
            }
            oneTour();
            for (Player player:players){
                System.out.println(player.quartierconstruit.size());
            }
            System.out.println("Tour "+round+" finish");
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

    void addChooseList(){
        for (Personnas.Personnage p:invisiblePersonage){
            chooseList.add(p);
        }
        for (Personnas.Personnage p:ListeDePersonagePourChoisir){
            chooseList.add(p);
        }
    }





    public static void main(String...args){
        Game game=new Game();
        System.out.println(game.players.size()+" init");
        System.out.println("game start");
        game.play();
        System.out.println(game.play());
    }
}
