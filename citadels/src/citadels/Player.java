package citadels;

import java.util.*;

public abstract class Player {
    Personnas.Personnage personnage;
    int argent;
    int points;
    boolean isKilled;
    boolean isStolen;
    List<Quartiers.Quartier> quartierenmain = new ArrayList<>();
    List<Quartiers.Quartier> quartierconstruit = new ArrayList<>();


    Player(Personnas.Personnage personage) {
        this.isKilled=false;
        this.isStolen=false;
        this.argent = 2;
        this.points = 0;
        this.personnage = personage;
    }


    int countquartier() {
        //count different quartier(has different name)
        int i = 0;
        List<String> names = new ArrayList<>();
        for (Quartiers.Quartier q : quartierenmain) {
            names.add(q.toString());
        }
        for (Quartiers.Quartier p : quartierconstruit) {
            names.add(p.toString());
        }
        Set<String> set = new HashSet<String>();
        set.addAll(names);
        i = set.size();
        return i;
    }

    void addargent() {
        this.argent = this.argent + 2;
    }

    void get1Quartiers(List<Quartiers.Quartier> quartiers) {
        List<Quartiers.Quartier> get2quartiers = new ArrayList<>();
        if (quartiers.size() > 0) {
            Random number = new Random();
            int a = number.nextInt(quartiers.size());
            get2quartiers.add(quartiers.get(a));
            quartiers.remove(a);
            if (quartiers.size() > 0) {
                int b = number.nextInt(quartiers.size());
                get2quartiers.add(quartiers.get(b));
                quartiers.remove(b);
                int c = number.nextInt(2);
                Quartiers.Quartier selectquartier = get2quartiers.get(c);
                get2quartiers.remove(c);
                quartiers.add(get2quartiers.get(0));
                quartierenmain.add(selectquartier);
            } else {
                quartierenmain.add(get2quartiers.get(0));
            }
        }
    }

    void construitquartier() {
        boolean exist = false;
        for (int i = 0; i < quartierenmain.size(); i++) {
            for (Quartiers.Quartier m : quartierconstruit) {
                if (quartierenmain.get(i) == m) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                if (this.argent >= quartierenmain.get(i).price) {
                    this.argent = this.argent - quartierenmain.get(i).price;
                    quartierconstruit.add(quartierenmain.get(i));
                    quartierenmain.remove(i);
                    return;
                }
            }
        }
    }

    abstract void action(List<Quartiers.Quartier> quartiers);

    void countpoints() {
        for (Quartiers.Quartier quartier : quartierconstruit) {
            this.points = this.points + quartier.price;
        }
    }

    Player getWinner(List<Player> players) {
        int max = players.get(0).points;
        int position = 0;
        for (int m = 1; m < players.size(); m++) {
            if (max < players.get(m).points) {
                max = players.get(m).points;
                position = m;
            }
        }
        int same = 0;
        int[] location = new int[7];
        for (int m = 0; m < players.size(); m++) {
            if (players.get(m).points == max) {
                location[same] = m;
                same = same + 1;

            }
        }
        if (same == 1) {
            return players.get(position);
        } else {
            for (int m : location) {
                if (players.get(position).personnage.getNumber() < players.get(m).personnage.getNumber()) {
                    position = m;
                }
            }
            return players.get(position);
        }
    }

    void action_assassin(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        Random random=new Random();
        List<Personnas.Personnage> lista=list1;
        lista.remove(list1.indexOf(Personnas.Personnage.Assassin));
        int s = random.nextInt(list1.size());
        for(Player j:list2){
            if(j.personnage==list1.get(s)){
                j.isKilled=true;
                break;
            }
        }
        action(quartiers);
        construitquartier();
    }

    void action_voleur(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        List<Personnas.Personnage> lista=list1;
        lista.remove(list1.indexOf(Personnas.Personnage.Voleur));
        Random random=new Random();
        int s = random.nextInt(list1.size());
        for(Player j:list2){
            while (j.personnage!=list1.get(s)||j.personnage== Personnas.Personnage.Assassin||j.isKilled==true){
                s=random.nextInt(list1.size());
            }
            if(j.personnage==list1.get(s)&&j.personnage!= Personnas.Personnage.Assassin&&j.isKilled!=true){
                this.argent=this.argent+j.argent;
                j.argent=0;
                break;
            }
        }
        action(quartiers);
        construitquartier();
    }

    void action_magicien(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        List<Personnas.Personnage> lista=list1;
        lista.remove(list1.indexOf(Personnas.Personnage.Magicien));
        Random random=new Random();
        int s = random.nextInt(2);
        if(s==0){
            int m = random.nextInt(list1.size());
            for(Player j:list2){
                if(j.personnage==list1.get(m)){
                    List<Quartiers.Quartier> a = j.quartierenmain;
                    j.quartierenmain=this.quartierenmain;
                    this.quartierenmain=a;
                    break;
                }
            }
        }else {
            int n = random.nextInt(this.quartierenmain.size());
            for (int y=0;y<n;y++){
                quartiers.add(this.quartierenmain.get(0));
                this.quartierenmain.remove(0);
                this.quartierenmain.add(quartiers.get(0));
                quartiers.remove(0);
            }
        }
        action(quartiers);
        construitquartier();
    }

    void action_roi(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        action(quartiers);
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.couleur == "jaune") {
                this.argent = this.argent + 1;
            }
        }
        construitquartier();
    }

    void action_eveque(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        action(quartiers);
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.couleur == "bleue") {
                this.argent = this.argent + 1;
            }
        }
        construitquartier();
    }

    void action_marchant(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        this.argent=this.argent+1;
        action(quartiers);
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.couleur == "vert") {
                this.argent = this.argent + 1;
            }
        }
        construitquartier();
    }

    void action_architect(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        Random random=new Random();
        int m = random.nextInt(2);
        int n = random.nextInt(3)+1;
        action(quartiers);
        if(m==0){
            this.quartierenmain.add(quartiers.get(0));
            quartiers.remove(0);
            this.quartierenmain.add(quartiers.get(0));
            quartiers.remove(0);
        }
        for(int i=0;i<n;i++){
            construitquartier();
        }
    }

    void action_condottiere(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers){
        action(quartiers);
        construitquartier();
        List<List<Quartiers.Quartier>> destroy=new ArrayList<>();
        for(Player p:list2){
            if(p.personnage!= Personnas.Personnage.Eveque){
                destroy.add(p.quartierconstruit);
            }else {
                if(p.isKilled==true){
                    destroy.add(p.quartierconstruit);
                }
            }
        }
        Random random=new Random();
        int s = random.nextInt(destroy.size());
        int x=random.nextInt(destroy.get(s).size());
        while (destroy.get(s).get(x).price-1>this.argent){
            s = random.nextInt(destroy.size());
            x=random.nextInt(destroy.get(s).size());
        }
        if(destroy.get(s).get(x).price-1<=this.argent){
            for(Player p:list2){
                if(p==destroy.get(s)){
                    for(int i=0;i<p.quartierconstruit.size();i++){
                        if(p.quartierconstruit.get(i)==destroy.get(s).get(x)){
                            this.argent=this.argent-p.quartierconstruit.get(i).price+1;
                            p.quartierconstruit.remove(i);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}
