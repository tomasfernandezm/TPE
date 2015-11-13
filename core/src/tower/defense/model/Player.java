package tower.defense.model;

/**
 * Created by Tomi on 08/11/2015.
 */
public class Player {

    private int money;
    private int level;
    private int lives;

    public Player(){
        money = 0;
        level = 1;
        lives = 50;
    }

    public int getLives() {
        return lives;
    }

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public void addMoney(int money){
        this.money = getMoney() + money;
    }

    public void printString(){
        System.out.println("Vida: " + getLives() + " Plata: " + getMoney());
    }
}
