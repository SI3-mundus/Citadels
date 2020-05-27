package citadels;

import java.util.*;


public class Game {
    List<Player> players = new ArrayList<>();
    Quartiers quar=new Quartiers();
    int round=1;
    boolean finish=false;
    List<Quartiers.Quartier> giveUpQuartiers=new ArrayList<>();
    List<Personnas.Personnage> ListeDePersonagePourChoisir=new ArrayList<>();
    List<Personnas.Personnage> visiblePersonage=new ArrayList<>();
    List<Personnas.Personnage> invisiblePersonage=new ArrayList<>();
    List<Personnas.Personnage> chooseList=new ArrayList<>();
    int playersnumber;

    //  Main()
    public static void main(String...args){
        Game game=new Game();
        System.out.println(game.players.size()+" init");
        System.out.println(game.play());
    }

    Game(){
        this.playersnumber=4;
        getPersonnagesPourChoisir();
        players.add(new Dumb(ListeDePersonagePourChoisir.get(0)));
        players.add(new Dumb(ListeDePersonagePourChoisir.get(1)));
        players.add(new Smart(ListeDePersonagePourChoisir.get(2)));
        players.add(new Smart(ListeDePersonagePourChoisir.get(3)));
        players.get(0).isRoilasttour=true;
        quar.shuffle();
        for (Player player:players){
            player.quartierenmain=quar.get4quartiers();
        }
        for(Player player:players){
            if(player.personnage== Personnas.Personnage.Roi){
                player.isRoilasttour=true;
            }
        }

    }
    //国王是否可见
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


    //角色选择
    List<Personnas.Personnage> getPersonnagesPourChoisir(){
        Personnas personas=new Personnas();
        personas.shuffle();
        switch (playersnumber){
            case 4:
                visiblePersonage=personas.takeAway(2);
                invisiblePersonage=personas.takeAway(1);
                ListeDePersonagePourChoisir=personas.personnas;
                if(isRoisvisible()){getPersonnagesPourChoisir();}
                addChooseList();
                break;
            case 5:
                visiblePersonage=personas.takeAway(1);
                invisiblePersonage=personas.takeAway(1);
                ListeDePersonagePourChoisir=personas.personnas;
                if(isRoisvisible()){getPersonnagesPourChoisir();}
                addChooseList();
                break;
            case 6:
                visiblePersonage=personas.takeAway(0);
                invisiblePersonage=personas.takeAway(1);
                ListeDePersonagePourChoisir=personas.personnas;
                if(isRoisvisible()){getPersonnagesPourChoisir();}
                addChooseList();
                break;
        }
        return ListeDePersonagePourChoisir;
    }

    //决定谁是下一轮第一选角色的
    void deciderquiRoi(){
        boolean existedRoi=false;
        for(Player p:players){
            if(p.personnage== Personnas.Personnage.Roi){
                existedRoi=true;
                break;
            }
        }
        if(existedRoi=true){
            for(Player p:players){
                if(p.personnage== Personnas.Personnage.Roi){
                    p.isRoilasttour=true;
                }else {
                    p.isRoilasttour=false;
                }
            }
        }
    }

    //分配角色,从上一轮的roi开始选
    List<Player> choisirPersonnage(){
        if(players.size()<ListeDePersonagePourChoisir.size()){
            int a=0;
            for (int i=0;i<players.size();i++){
                if(players.get(i).isRoilasttour==true){
                    players.get(i).personnage=ListeDePersonagePourChoisir.get(0);
                }else {a=a+1;}
                break;
            }
            int b=1;
            for (int i=0;i<players.size();i++){
                if(i!=a){
                    players.get(i).personnage=ListeDePersonagePourChoisir.get(b);
                    b=b+1;
                }
            }
            return players;
        }else {
            return null;
        }
    }



    void oneTour(){
        for (int i=1;i<9;i++) {
            callPersonnage(i);
        }
    }

    List<Player> getOtherPlayers(Player player){
        List<Player> otherPlayers = new ArrayList<>();
        for (Player eachPlayer: players){
            if (eachPlayer != player){
                otherPlayers.add(eachPlayer);
            }
        }
        return otherPlayers;
    }


    //一个游戏回合,依角色顺序循环
    void callPersonnage(int i){
            switch (i) {
                case 1:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 1) {

//                            这里我想的话,玩家的决策应该写到玩家的类里面，这个Game只是控制游戏流程不应该统一所有玩家的行为
//                            其实这个角色的技能也应该放到每个玩家的类里面，因为每个难度的玩家对每个角色的用法都不一样.

//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n) == Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("l'assassin a commence son tour");

                            //传递场上其他玩家到action里做判断
                            player.action_assassin(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("l'assassin a fini son tour.");
                            break;
                        }
                    }
                    break;
                case 2:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 2 && !player.isKilled) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("le voleur a commence son tour");

                            player.action_voleur(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("le voleur a fini son tour.");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                case 3:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 3 && !player.isKilled) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("le magicien a commence son tour");

                            player.action_magicien(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("le magicien a fini son tour");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                case 4:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 4 && player.isKilled != true) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("le roi a commence son tour");

                            player.action_roi(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("le roi a fini son tour.");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                case 5:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 5 && player.isKilled != true) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("l'eveque a commence son tour");

                            player.action_eveque(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("l'eveque a fini son tour.");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                case 6:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 6 && player.isKilled != true) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("le marchant a commence son tour");

                            player.action_marchant(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("le marchant a fini son tour.");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                case 7:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 7 && player.isKilled != true) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("l'architect a commence son tour");

                            player.action_architect(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("l'architect a fini son tour");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                case 8:
                    for (Player player : players) {
                        if (player.personnage.getNumber() == 8 && player.isKilled != true) {
//                            for (int n=0;n<player.quartierconstruit.size();n++){
//                                if (player.quartierconstruit.get(n)== Quartiers.Quartier.Laboratoire){
//                                    player.quartierenmain.remove(0);
//                                    player.argent ++;
//                                }
//                            }
                            System.out.println("le cordittiere a commence son tour");

                            player.action_condottiere(chooseList,players,quar.quartiers, getOtherPlayers(player));
                            System.out.println("le condottiere a fini son tour");
                            break;
                        }else if(player.isKilled){
                            System.out.println("Il a ete tue");
                            player.isKilled=false;}
                    }
                    break;
                default:
                    System.exit(2);
            }

    }


    String play(){
        if(round==1){
            System.out.println("Game Start");
            System.out.println("Les quatre jouers sont Dumb1, Dumb2, Smart1 et Smart2");
            System.out.println("les joueurs ont choisi ces personages(liste en ordre):" );
            int i = 0;
            for (Player player:players){
//                玩家选择了这些角色（按顺序列出）
                System.out.println("Joueur "+(++i)+": "+player.personnage);
            }
            oneTour();
            System.out.println("Le nombre de quartiers construit par chaque joueurs est:");
            i = 0;
            for (Player player:players){
//                每个玩家建造的建筑数量
                System.out.println("Joueur "+(++i)+": "+player.quartierconstruit.size());
            }
            System.out.println("Tour "+round+" finish");
        }
        while (!finish){

            round=round+1;
            System.out.println("Tour "+round+" start");
            deciderquiRoi();
            getPersonnagesPourChoisir();
            choisirPersonnage();
            System.out.println("les joueurs ont choisi ces personages(liste en ordre):" );
            for (Player player:players){
                System.out.println(player.personnage);
            }
            oneTour();
            System.out.println("Le nombre de quartiers construit par chaque joueurs est:");
            for (Player player:players){
                System.out.println(player.quartierconstruit.size());
            }
            System.out.println("les quartiers que chaqun a construit est:");
            for (Player player:players) {
                for (int i = 0; i < player.quartierconstruit.size(); i++) {
                    System.out.println(player.quartierconstruit.get(i));
                }
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
            System.out.println("the current point is");
            System.out.println(player.countpoints());
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
        System.out.println("le joueur gagne est:");
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






}
