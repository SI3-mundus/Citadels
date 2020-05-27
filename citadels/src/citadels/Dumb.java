package citadels;

import java.util.List;
import java.util.Random;

public class Dumb extends Player{
//  Dumb 玩家：有钱有卡就造. 不分颜色，不看身份，不算总分，不用技能，佛系玩家
    Dumb(Personnas.Personnage personage) {
        super(personage);
    }

    @Override
    Personnas.Personnage choisirSonPersonnage(List<Personnas.Personnage> list1){
        if(list1.contains(Personnas.Personnage.Roi)){
            this.personnage= Personnas.Personnage.Roi;
            list1.remove(Personnas.Personnage.Roi);
        }else {
            this.personnage=list1.get(0);
            list1.remove(0);
        }
        return this.personnage;
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers, List<Player> otherPlayer){

        //1. 获取场上信息
        countcurrentpoints();
        int crntPoint = currentpoints; //分数
        int crntMoney = argent; //金币
        int crntHandCards = availablenNumberofquartiersenmain(); //建筑手牌数
        int crntBuildings = quartierconstruit.size(); //已有建筑数
//        System.out.println("crntMoney "+ crntMoney);
//        System.out.println("crntHandCards "+crntHandCards);
//        System.out.println("crntBuildings "+crntBuildings);
        //2. 抽卡或金币阶段:
        if (quartiers.size() > 0) {
            if (countquartier() < 7) {
                //有图书馆：但是选择要钱

                if (quartierconstruit.contains(Quartiers.Quartier.Bibliotheque)) {
                    System.out.println("Il ne choisit pas un comportement particulier.");
                    addargent();
                }else{
                    if(crntHandCards <= 1 || availablenNumberofquartiersenmain() < 1) {
                        get1Quartiers(quartiers);
                    }else {
                        System.out.println("Il ne choisit pas un comportement particulier.");
                        addargent();
                    }

                }
            }

        } else {
            //如果建筑卡组已用完，则只加钱
            System.out.println("Il ne choisit pas un comportement particulier.");
            addargent();
        }

        //3. 建筑技能阶段:

        //如果有实验室：统一不用技能
        if (quartierconstruit.contains(Quartiers.Quartier.Laboratoire)) {
//            System.out.println("Dumb 玩家拥有实验室");
        }

        //4. 建造阶段： (你们这个建造是写死了我来不及改了)
        construitquartier();

    }
}
