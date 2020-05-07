package citadels;

import java.util.*;

public class Robot extends Player{
    //  Robot 玩家：有钱有卡就造. 不分颜色，不算总分，不看身份，选择性使用技能
    Robot(Personnas.Personnage personage) {
        super(personage);
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers, List<Player> otherPlayer){
        //1. 获取场上信息
        countpoints();
        int crntPoint = points; //分数
        int crntMoney = argent; //金币
        int crntHandCards = availablenNumberofquartiersenmain(); //建筑手牌数
        int crntBuildings = quartierconstruit.size(); //已有建筑数
        System.out.println("crntMoney "+ crntMoney);
        System.out.println("crntHandCards "+crntHandCards);
        System.out.println("crntBuildings "+crntBuildings);

        //2. 抽卡或金币阶段:
        if (quartiers.size() > 0) {
            if (countquartier() < 7) {
                //如果有图书馆：
                if (quartierconstruit.contains(Quartiers.Quartier.Bibliotheque)) {

                    //如果缺建筑手牌(小于等于1),才选择发动技能拿2张牌,这个你们可以看情况改
                    if (crntHandCards <= 1 || availablenNumberofquartiersenmain() < 1) {
                        get2Quartiers(quartiers);
                    } else {
                        //手牌>=2, 拿钱
                        addargent();
                    }

                    //这里如果加了其它的特殊卡，后面跟if就行了
                } else {
                    //没有带被动技能的建筑
                    if (crntHandCards <= 1 || availablenNumberofquartiersenmain() < 1) {
                        get1Quartiers(quartiers);
                    } else {
                        addargent();
                    }
                }
            }
        } else {
            //如果建筑卡组已用完，则只加钱
            addargent();
        }

        //3. 建筑技能阶段:

        //如果有实验室：不卖手牌
        if (quartierconstruit.contains(Quartiers.Quartier.Laboratoire)) {
//            System.out.println("Dumb 玩家拥有实验室");
        }

        //4. 建造阶段：
        construitquartier();
    }
}