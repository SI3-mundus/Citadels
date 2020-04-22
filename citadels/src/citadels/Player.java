package citadels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    Personnas.Personnage personnage;
    int argent;
    List<Quartiers.Quartier> quartierenmain;
    List<Quartiers.Quartier> quartierconstruit;
    Player(Personnas.Personnage personae){
        this.argent=2;
        this.personnage=personae;
        List<Quartiers.Quartier> quartierenmain=new ArrayList<>();
        List<Quartiers.Quartier> quartierconstruit=new ArrayList<>();
    }

    void addargent(){
        this.argent=this.argent+2;
    }

    void get1Quartiers(List<Quartiers.Quartier> quartiers){
        List<Quartiers.Quartier> get2quartiers=new ArrayList<>();
        Random number = new Random();
        int a=number.nextInt(quartiers.size());
        get2quartiers.add(quartiers.get(a));
        quartiers.remove(a);
        int b=number.nextInt(quartiers.size());
        get2quartiers.add(quartiers.get(b));
        quartiers.remove(b);
        int c=number.nextInt(2);
        Quartiers.Quartier selectquartier=get2quartiers.get(c);
        get2quartiers.remove(c);
        quartiers.add(get2quartiers.get(0));
        quartierenmain.add(selectquartier);
    }

    void construitquartier(){
        boolean exist=true;
        for(int i=0;i<quartierenmain.size();i++){
            for(Quartiers.Quartier m:quartierenmain){
                if (quartierenmain.get(i)==m){
                    exist=false;
                    break;
                }
            }
            if (exist){
                if (this.argent>=quartierenmain.get(i).price){
                    this.argent=this.argent-quartierenmain.get(i).price;
                    quartierconstruit.add(quartierenmain.get(i));
                    quartierenmain.remove(i);
                    break;
                }
            }
        }
    }

    public static void main(String...args){
        Player p=new Player(Personnas.Personnage.Condottiere);
        p.addargent();
        System.out.println(p.personnage.getNumber());
        System.out.println(p.argent);
    }

}
