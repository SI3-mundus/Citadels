package citadels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quartiers {
    enum Quartier {
        Temple("bleue", 1, 3), Eglise("bleue", 2, 3),
        Monastere("bleue", 3, 4), Cathedrale("bleue", 5, 2),
        Tourdeguet("rouge", 1, 3), Prison("rouge", 2, 3),
        Caserne("rouge", 3, 3), Forteresse("rouge", 5, 2),
        Manoir("jaune", 3, 5), Chateau("jaune", 4, 4),
        Palais("jaune", 5, 2),
        Taverne("vert", 1, 5), Echoppe("vert", 2, 3),
        Marche("vert", 2, 4), Comptoir("vert", 3, 3),
        Port("vert", 4, 3), Hoteldeville("vert", 5, 2),

        Courdesmiracles("violet", 2, 1),
        Donjon("violet", 3, 2),
        Laboratoire("violet", 5, 1),
        Manufacture("violet", 5, 1),
        Observatoire("violet", 5, 1),
        Cimetiere("violet", 5, 1),
        Bibliotheque("violet", 6, 1),
        EcoleDeMagie("violet", 6, 1),
        Universite("violet", 6, 1),
        Dracoport("violet", 6, 1);

        private String couleur;
        private int price;
        private int quantity;
        //建筑卡构造函数
        Quartier(String couleur, int price, int quantity) {
            this.couleur = couleur;
            this.price = price;
            this.quantity = quantity;
        }
        //代码里面.price和getPrice()都在用，看着乱我就统一用getter了，属性前面加了private
        String getCouleur(){return couleur;}
        int getPrice(){return price;}
        int getQuantity(){return quantity;}
    }

//  牌组构建函数
    List<Quartier> quartiers=new ArrayList<>();
    Quartiers(){
        for (Quartier quar : Quartier.values()){
            for(int i=0;i<quar.quantity;i++){
                quartiers.add(quar);
            }
        }
    }

//  洗牌
    void shuffle(){
        Collections.shuffle(quartiers);
    }

//  返还四张建筑卡
    List<Quartier> get4quartiers(){
        shuffle();
        List<Quartier> get4=new ArrayList<>();
        for(int i=0;i<4;i++){
            get4.add(quartiers.get(0));
            quartiers.remove(0);
        }
        return get4;
    }

//     public static void main(String...args){
//         Quartiers q=new Quartiers();
        //System.out.println(q.quartiers.size()==65);
        //Personnas p=new Personnas();
        //System.out.println(p.personnas.toString());
//        Random random = new Random();
//        System.out.println(random.nextInt(0));
//        System.out.println(Quartier.Temple.ordinal());

}


