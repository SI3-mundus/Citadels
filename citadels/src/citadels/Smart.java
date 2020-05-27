package citadels;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import static jdk.nashorn.internal.objects.NativeArray.sort;

public class Smart extends Player{
    //  Smart 玩家：根据其他玩家的情况建造. 分已有的卡组颜色，计算总分，看自己的身份，主动建筑和被动建筑技能组合使用
    //  这个角色的倾向是让自己的现有分数和潜在分数的和(总分数)在全场最高
    Smart(Personnas.Personnage personage) {
        super(personage);
    }
    public Map<String,Integer> frequencyOfListElements() {
        if (this.quartierconstruit == null || quartierconstruit.size() == 0) return null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Quartiers.Quartier temp : quartierconstruit) {
            Integer count = map.get(temp);
            map.put(temp.getCouleur(), (count == null) ? 1 : count + 1);
        }
        Map<String, Integer> sorted = map
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));


        return sorted;
    }

    @Override
    Personnas.Personnage choisirSonPersonnage(List<Personnas.Personnage> list1){
        Map<String,Integer> map=frequencyOfListElements();
        if(map==null){
            this.personnage=list1.get(0);
            list1.remove(0);
            return this.personnage;
        }
        for(String color: map.keySet()){
            switch (color){
                case "bleue":
                    if(list1.contains(Personnas.Personnage.Eveque)){
                        this.personnage= Personnas.Personnage.Eveque;
                        list1.remove(Personnas.Personnage.Eveque);
                        return personnage;
                    }
                    break;
                case "rouge":
                    if(list1.contains(Personnas.Personnage.Condottiere)){
                        this.personnage= Personnas.Personnage.Condottiere;
                        list1.remove(Personnas.Personnage.Condottiere);
                        return personnage;
                    }
                    break;
                case "jaune":
                    if(list1.contains(Personnas.Personnage.Roi)){
                        this.personnage= Personnas.Personnage.Roi;
                        list1.remove(Personnas.Personnage.Roi);
                        return personnage;
                    }
                    break;
                case "vert":
                    if(list1.contains(Personnas.Personnage.Marchand)){
                        this.personnage= Personnas.Personnage.Marchand;
                        list1.remove(Personnas.Personnage.Marchand);
                        return personnage;
                    }
                    break;
            }
        }
        this.personnage=list1.get(0);
        list1.remove(0);
        return this.personnage;

    }
    @Override
    void action(List<Quartiers.Quartier> quartiers, List<Player> otherPlayer){

        //1. 获取场上信息
        countcurrentpoints();
        int crntPoint = currentpoints; //现有分数
        int crntMoney = argent; //金币
        int crntHandCards = availablenNumberofquartiersenmain(); //手牌数
        int crntBuildings = quartierconstruit.size(); //已有建筑数
        int potentialPoints = countPotentialPoints(); //手牌潜在分数
        int playerTotalPoint = potentialPoints + crntPoint;
        int crntMaxTotalPoint = 0;
//        System.out.println("crntMoney "+ crntMoney);
//        System.out.println("crntHandCards "+crntHandCards);
//        System.out.println("crntBuildings "+crntBuildings);

            //找到最大分数值
        for (Player player: otherPlayer){
            if( (player.countPotentialPoints() + player.currentpoints) > crntMaxTotalPoint ){
                crntMaxTotalPoint = player.countPotentialPoints() + player.currentpoints;
            }
        }


        if (countquartier() < 7) {

            //2. 抽卡或金币阶段(影响抽卡或拿金币的建筑被动技能):
            if (quartiers.size() > 0) {

                //如果有图书馆：
                if (quartiers.contains(Quartiers.Quartier.Bibliotheque)) {

                    //如果自己身份是建筑师(如果玩家回合开始选择建筑师，肯定是想攒牌或建造)，则必定发动技能
                    //如果潜在分数小于场上最大总分数也发动技能
                    if (personnage == Personnas.Personnage.Architecte || (playerTotalPoint < crntMaxTotalPoint)){
                        get2Quartiers(quartiers);
                    }else{
                        //不是建筑师 -> 有实验室 -> 缺钱: 攒下牌组提高总分数或者选择换钱
                        if (quartierconstruit.contains(Quartiers.Quartier.Laboratoire) && crntMoney <= 1) {
                            get2Quartiers(quartiers);
                        }else{
                            //不是建筑师 -> 没有实验室：如果缺建筑手牌(小于等于1),才选择发动技能拿2张牌
                            if (crntHandCards <= 1 || availablenNumberofquartiersenmain() < 1){
                                get2Quartiers(quartiers);
                            }else{
                                //手牌>=2, 拿钱
                                addargent();
                            }
                        }
                    }

                    // ** 这里如果加了其它的特殊卡，后面跟if就行了 **
                }else{
                    //没有带被动技能的建筑
                    if(crntHandCards <= 1 || availablenNumberofquartiersenmain() < 1){
                        get1Quartiers(quartiers);
                    }else{
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
                //有实验室-> 手牌大于3 ->而且和潜在分数差距不大(差值<=2) -> 卖掉价值低的建筑卡： 攒钱给价值高的建筑卡
                if(crntHandCards >= 3 && (crntMaxTotalPoint - playerTotalPoint) <= 2){
                    //选择手牌里价值最低的一张卡
                    //后面个函数在Player.java的116行
                    laboratoire_ability(quartiers,lowestPriceCardIndex(quartierenmain));
                }
            }

            //4. 建造阶段：(可以根据场上的总分和自己拥有的卡组颜色做判断，时间关系我就不写了)
            construitquartier();
        }

    }
}
