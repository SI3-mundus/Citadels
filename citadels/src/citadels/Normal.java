package citadels;

import java.util.List;
import java.util.Random;

public class Normal extends Player{
    //  Normal 玩家：有钱有卡就造. 不分颜色，不算总分，看自己的身份，选择性使用技能
    Normal(Personnas.Personnage personage) {
        super(personage);
    }
    @Override
    void action(List<Quartiers.Quartier> quartiers, List<Player> otherPlayer) {

        //1. 获取场上信息
        countpoints();
        int crntPoint = points; //分数
        int crntMoney = argent; //金币
        int crntHandCards = availablenNumberofquartiersenmain(); //手牌数
        int crntBuildings = quartierconstruit.size(); //已有建筑数
        if (countquartier() < 7) {

        //2. 抽卡或金币阶段(影响抽卡或拿金币的建筑被动技能):
            if (quartiers.size() > 0) {
                //如果有图书馆：
                if (quartierconstruit.contains(Quartiers.Quartier.Bibliotheque)) {

                    //如果自己身份是建筑师(如果玩家回合开始选择建筑师，肯定是想攒牌或建造)，则必定发动技能
                    if (personnage == Personnas.Personnage.Architecte && quartiers.size()>=2) {
                        get2Quartiers(quartiers);
                    } else {
                        //如果缺建筑手牌(小于等于1),才选择发动技能拿2张牌
                        if (crntHandCards <= 1 && quartiers.size()>=2) {
                            get2Quartiers(quartiers);
                        } else {
                            //手牌>=2, 拿钱
                            addargent();
                        }
                    }

                    // ** 这里如果加了其它的特殊卡，后面跟if就行了 **
                } else {
                    //没有带被动技能的建筑
                    if (crntHandCards <= 1) {
                        get1Quartiers(quartiers);
                    } else {
                        addargent();
                    }
                }


            } else {
                //如果建筑卡组已用完，则只加钱
                addargent();
            }

        //3. 建筑主动技能阶段(已经抽完了卡，可以发动效果的):

            //如果有实验室：
            if (quartierconstruit.contains(Quartiers.Quartier.Laboratoire)) {
                //如果建筑卡比钱数量多 3 (根据情况改)
                if((crntHandCards - crntMoney) > 3){
                    //选择手牌里第一张卡(可以根据情况改)
                    laboratoire_ability(quartiers,0);
                }
            }

            //4. 建造阶段：(不做判断)
            construitquartier();
        }
    }
}
