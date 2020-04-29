package citadels;

import java.util.*;

public abstract class Player {
    Personnage.Personne personnage;
    int argent;
    int points;
    List<Quartiers.Quartier> quartierenmain = new ArrayList<>();
    ;
    List<Quartiers.Quartier> quartierconstruit = new ArrayList<>();
    ;

    Player(Personnage.Personne personae) {
        this.argent = 2;
        this.points = 0;
        this.personnage = personae;
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
        boolean exist = true;
        for (int i = 0; i < quartierenmain.size(); i++) {
            for (Quartiers.Quartier m : quartierconstruit) {
                if (quartierenmain.get(i) == m) {
                    exist = false;
                    break;
                }
            }
            if (exist) {
                if (this.argent >= quartierenmain.get(i).price) {
                    this.argent = this.argent - quartierenmain.get(i).price;
                    quartierconstruit.add(quartierenmain.get(i));
                    quartierenmain.remove(i);
                    break;
                }
            }
        }
    }

    void action(List<Quartiers.Quartier> quartiers) {
        Random n = new Random();
        if (quartiers.size() > 0) {
            if (countquartier() < 7) {
                int s = n.nextInt(100);
                if (s < 67) {
                    get1Quartiers(quartiers);
                } else {
                    addargent();
                }
            } else {
                int s = n.nextInt(10);
                if (s < 67) {
                    addargent();
                } else {
                    get1Quartiers(quartiers);
                }
            }
        } else {
            addargent();
        }
    }

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
}
//
//    public static void main(String...args){
//        Player p=new Player(Personnas.Personnage.Condottiere);
//        p.addargent();
//        System.out.println(p.quartierconstruit.size());
//        System.out.println(p.argent);
//    }
//
//}
