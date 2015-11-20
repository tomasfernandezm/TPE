package tower.defense.model;

/**
 * Created by Tomi on 08/11/2015.
 */
public class Player {

    private String name;
    private int money;
    private int level;
    private double score;
    private int lives;

    public Player(){
        money = 1000;
        level = 0;
        lives = 50;
        score = 0;
    }

    public double getScore() {
        return score;
    }

    public void increaseScore(int typeOfMinion) {
        this.score += getMoney()*getLevel() * getLives()* typeOfMinion/200;
    }

    public int getLives() {
        return lives;
    }

    public int getMoney() {
        return money;
    }

    public void upLevel(){
        level++;
    }

    public int getLevel() {
        return level;
    }

    public boolean isOver() {
        return lives <= 0;
    }

    public void spendLife(int typeOfMinion) {
        lives -= typeOfMinion;
    }

    public void addMoneyMinionKill(int typeOfMinion){
        money += typeOfMinion*25;
    }

    public void spendMoney(int money){ this.money -= money; }

    public void printString(){
        System.out.println(name + " Vida: " + getLives() + ", Plata: " + getMoney() + ", Nivel: " + getLevel() + ", Score: " + getScore());
    }

    public void setName(String text){
        name = text;
    }

    public String getName(){
        return name;
    }
}
