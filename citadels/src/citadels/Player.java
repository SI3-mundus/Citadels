package citadels;

import java.util.*;

import static citadels.Quartiers.Quartier;

public abstract class Player {

    Personnas.Personnage personnage;
    int argent;//钱
    int points;
    int currentpoints;
    boolean isKilled;
    boolean isStolen;
    boolean isRoilasttour;
//    建筑卡手牌
    List<Quartiers.Quartier> quartierenmain = new ArrayList<>();
//    已买下的建筑
    List<Quartiers.Quartier> quartierconstruit = new ArrayList<>();


    Player(Personnas.Personnage personage) {
        this.isRoilasttour=false;
        this.isKilled=false;
        this.isStolen=false;
        this.argent = 2;
        this.points = 0;
        this.personnage = personage;
    }

    boolean verifyisRoi(){
        if(this.personnage== Personnas.Personnage.Roi){
            isRoilasttour=true;
        }
        return isRoilasttour;
    }

    Personnas.Personnage choisirSonPersonnage(List<Personnas.Personnage> list1){return this.personnage;}


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
        this.argent += 2;
        System.out.println( "Il a choisi deux pièces d'or.");
    }

    //获得一张建筑卡
    void get1Quartiers(List<Quartiers.Quartier> quartiers) {
        List<Quartiers.Quartier> get2quartiers = new ArrayList<>();
        if (quartiers.size() > 0) {

            //获得牌组内随机一张建筑卡 -> get2quartiers
            Random number = new Random();
            int a = number.nextInt(quartiers.size());
            get2quartiers.add(quartiers.get(a));
            quartiers.remove(a);//弃掉的牌用quartiers.add重新放回底层？

            if (quartiers.size() > 0) {
                //抽第二张建筑卡 -> get2quartiers
                int b = number.nextInt(quartiers.size());
                get2quartiers.add(quartiers.get(b));
                quartiers.remove(b);

                //随机二选一
                int c = number.nextInt(2);
                Quartiers.Quartier selectquartier = get2quartiers.get(c);
                get2quartiers.remove(c);
                //另一张放回卡组
                quartiers.add(get2quartiers.get(0));
                quartierenmain.add(selectquartier);
                System.out.println("Il a choisi une carte de construction.");
                System.out.println("La carte qu'il a choisie est: " + selectquartier);
            } else {
                Quartiers.Quartier selectquartier = get2quartiers.get(0);
                quartierenmain.add(selectquartier);
                System.out.println("Il a choisi une carte de construction.");
                System.out.println("La carte qu'il a choisie est: " + selectquartier);
            }

        }

    }

    //有图书馆时获得两张建筑卡
    void get2Quartiers(List<Quartiers.Quartier> quartiers) {
        List<Quartiers.Quartier> get2quartiers = new ArrayList<>();
        //其实这里牌组size的判定应该放到玩家回合开始，因为牌不够不允许发动技能
        if (quartiers.size() > 1) {

            //获得牌组内随机一张建筑卡 -> get2quartiers
            Random number = new Random();
            int a = number.nextInt(quartiers.size());
            get2quartiers.add(quartiers.get(a));
            quartiers.remove(a);

            if (quartiers.size() > 0) {
                //抽第二张建筑卡 -> get2quartiers
                int b = number.nextInt(quartiers.size());
                get2quartiers.add(quartiers.get(b));
                quartiers.remove(b);
            }
            quartierenmain.add(get2quartiers.get(0));
            quartierenmain.add(get2quartiers.get(1));
            System.out.println("Il a choisi deux cartes de construction.");
            System.out.println("La carte qu'il a choisie est: " + get2quartiers.get(0)+" et "+get2quartiers.get(1));
        }
    }

    //实验室的技能：选择一张卡放回卡组，然后金币加一
    void laboratoire_ability(List<Quartiers.Quartier> quartiers, int index){
        System.out.println("the ability of the lab is activated");
        if(index >= 0){
            quartiers.add(quartierenmain.get(index));
            quartierenmain.remove(index);
            argent ++;
        }else{
            System.out.println("Index can't be smaller than 0");
            System.out.println("activation failed");
        }
    }

    //返还手牌里价值最低的一张卡的index
    int lowestPriceCardIndex(List<Quartiers.Quartier> handCards){
        if(handCards.size() > 0){
            int temp = 10;
            int result = 0;
            for (int i = 0; i<handCards.size(); i++){
                if(handCards.get(i).getPrice() <= temp){
                    result = i;
                }
            }
            return result;
        }else{
            return  0;
        }
    }

//  建造:如果是新建筑就建造手牌里能够建造的建筑 (不能根据情况选择建造哪种建筑)
    void construitquartier() {
        boolean exist = false;
        //循环每一张建筑手牌
        for (int i = 0; i < quartierenmain.size(); i++) {
            //循环拥有的建筑
            for (Quartiers.Quartier m : quartierconstruit) {
                //如果已有相同建筑就break
                if (quartierenmain.get(i).equals(m)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                if (this.argent >= quartierenmain.get(i).getPrice()) {
                    this.argent = this.argent - quartierenmain.get(i).getPrice();
                    quartierconstruit.add(quartierenmain.get(i));
                    System.out.println("il a construit le quartier:" + quartierenmain.get(i));
                    quartierenmain.remove(i);
                    return;
                }
            }
        }
    }

    //不同玩家对手牌执行不同步骤
    // **! 这里我加了一个参数，在game里面传递场上剩下的玩家List来获取场上的信息 !**
    abstract void action(List<Quartiers.Quartier> quartiers, List<Player> otherPlayers);

//    计算分数
    int countpoints() {
        for (Quartiers.Quartier quartier : quartierconstruit) {
            if (quartier == Quartier.Dracoport||quartier==Quartier.Universite) {
                this.points = this.points + quartier.getPrice() + 2;
            } else {
                this.points = this.points + quartier.getPrice();
            }
        }
        return points;
    }
    //    计算分数
    int countcurrentpoints() {
        for (Quartiers.Quartier quartier : quartierconstruit) {
            if (quartier == Quartier.Dracoport||quartier==Quartier.Universite) {
                this.currentpoints = this.currentpoints + quartier.getPrice() + 2;
            } else {
                this.currentpoints = this.currentpoints + quartier.getPrice();
            }
        }
        return currentpoints;
    }
//    如果手中的牌全部建造的分数(不加已有分数)
    int countPotentialPoints(){
        int total = 0;
        for (Quartiers.Quartier quar: quartierenmain){
            //有龙门则分数+2
            if(quar != Quartier.Dracoport){
                total += quar.getPrice();
            }else{
                total += quar.getPrice() + 2;
            }
        }
        return total;
    }



    //刺客
    void action_assassin(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        Random random=new Random();
        List<Personnas.Personnage> lista=list1;
        lista.remove(list1.indexOf(Personnas.Personnage.Assassin));
        int s = random.nextInt(list1.size());
        for(Player j:list2){
            //随机杀死一名角色 (不能根据情况选择杀死的玩家)
            if(j.personnage == list1.get(s) && j.personnage != Personnas.Personnage.Assassin){//这里是因为有一次我跑的时候刺客把自己杀死了 D:
                j.isKilled=true;
//                System.out.println("Personage: "+j.personnage.getName()+ " has been killed.");
                break;
            }
        }
        //执行
        action(quartiers, otherPlayers);
//        construitquartier();
    }

    //小偷
    void action_voleur(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        List<Personnas.Personnage> lista=list1;
        lista.remove(list1.indexOf(Personnas.Personnage.Voleur));
        Random random=new Random();
        int s = random.nextInt(list1.size());
        for(Player j:list2){
            while (j.personnage != list1.get(s)){
                s = random.nextInt(list1.size());
                if(j.personnage == Personnas.Personnage.Assassin || j.isKilled != true){break;}
            }
            if(j.personnage == list1.get(s) && j.personnage!= Personnas.Personnage.Assassin && j.isKilled != true){
                this.argent=this.argent+j.argent;
                j.argent=0;
                break;
            }
        }
        action(quartiers, otherPlayers);
//        construitquartier();
    }

    //魔法师
    void action_magicien(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        List<Personnas.Personnage> lista=list1;
        lista.remove(Personnas.Personnage.Magicien);
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
            int n = 1;
            //Bound positive 报错
            if (this.quartierenmain.size() > 0 ){
                n = random.nextInt(this.quartierenmain.size());
            }else {n = 0;}
            for (int y=0;y<n;y++){
                quartiers.add(this.quartierenmain.get(0));
                this.quartierenmain.remove(0);
                this.quartierenmain.add(quartiers.get(0));
                quartiers.remove(0);
            }
        }
        action(quartiers, otherPlayers);
//        construitquartier();
    }

    //国王
    void action_roi(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        action(quartiers, otherPlayers);
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.getCouleur() == "jaune") {
                this.argent = this.argent + 1;
            }
        }
//        construitquartier();
    }

    //主教
    void action_eveque(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        action(quartiers, otherPlayers);
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.getCouleur() == "bleue") {
                this.argent = this.argent + 1;
            }
        }
//        construitquartier();
    }

    //商人
    void action_marchant(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        this.argent=this.argent+1;
        action(quartiers, otherPlayers);
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.getCouleur() == "vert") {
                this.argent = this.argent + 1;
            }
        }
//        construitquartier();
    }

    //建筑师
    void action_architect(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        Random random=new Random();
        int m = random.nextInt(2);
        //在action里面包含了一次带决策的建造，这里就把范围改到了 0 ~ 2
        int n = random.nextInt(3);
        action(quartiers, otherPlayers);
        if(quartiers.size()>0) {
            if (m == 0) {
                this.quartierenmain.add(quartiers.get(0));
                quartiers.remove(0);
                this.quartierenmain.add(quartiers.get(0));
                quartiers.remove(0);
            }
        }
        //这里玩家不能选择建造的建筑数
        for(int i=0;i<n;i++){
            construitquartier();
        }
    }

    //领主
    void action_condottiere(List<Personnas.Personnage> list1, List<Player> list2,List<Quartiers.Quartier> quartiers, List<Player>otherPlayers){
        action(quartiers, otherPlayers);
//        construitquartier();
        List<List<Quartiers.Quartier>> destroy=new ArrayList<>();
        for (Quartiers.Quartier construit:this.quartierconstruit) {
            if (construit.getCouleur() == "rouge") {
                this.argent = this.argent + 1;
            }
        }
        for(Player p:list2){
            if(p.personnage!= Personnas.Personnage.Eveque){
                destroy.add(p.quartierconstruit);
            }else {
                if(p.isKilled==true){
                    destroy.add(p.quartierconstruit);
                }
            }
        }
        List<int[]> ableToDestroy = new ArrayList<>();
        for (int i = 0; i < destroy.size(); i++) {
            for (int j =  0; j < destroy.get(i).size(); j++) {
                if (destroy.get(i).get(j).getPrice() - 1 <= this.argent) {
                    ableToDestroy.add(new int[]{i,j});
//                    System.out.println(i+" "+j);
                    break;
                }
            }
        }
        if(ableToDestroy.size()!=0) {
            //这里不能根据情况选择摧毁的建筑
            Random random = new Random();
            int k = random.nextInt(ableToDestroy.size());
            int s = ableToDestroy.get(k)[0];
            int x = ableToDestroy.get(k)[1];
            if (destroy.get(s).size() > 0 && destroy.get(s).get(x) != null) {
                if (destroy.get(s).get(x).getPrice() - 1 <= this.argent) {
                    for (Player p : list2) {
                        if (p.quartierconstruit == destroy.get(s)) {
                            for (int i = 0; i < p.quartierconstruit.size(); i++) {
                                if (p.quartierconstruit.get(i) == destroy.get(s).get(x) && p.quartierconstruit.get(i) != Quartier.Donjon) {
                                    this.argent = this.argent - p.quartierconstruit.get(i).getPrice() + 1;
//                                    System.out.println(p.quartierconstruit.get(i)+" has destroied");
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

    }
    //可用建筑手牌数
    int availablenNumberofquartiersenmain(){
        List<String> construit = new ArrayList<>();
        for (Quartiers.Quartier p : quartierconstruit) {
            construit.add(p.toString());
        }
        Set<String> set1 = new HashSet<String>();
        set1.addAll(construit);

        List<String> main = new ArrayList<>();
        for (Quartiers.Quartier q : quartierenmain) {
            main.add(q.toString());
        }
        Set<String> set2 = new HashSet<String>();
        set2.addAll(main);

        int sizeUnusable=0;
        for(String a:set2){
            for(String b:set1){
                if (a==b){
                    sizeUnusable=sizeUnusable+1;
                }
            }
        }

        return set2.size()-sizeUnusable;
    }
}
