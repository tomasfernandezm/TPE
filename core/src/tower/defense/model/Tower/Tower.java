package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.Projectile;
import tower.defense.model.Player;

import java.util.List;

/**
 * Created by Tomi on 08/11/2015.
 */
public class Tower extends Entity {
    private float range;
    private float delay;
    private float timer = 0;
    private final static float WIDTH = 40;
    private final static float HEIGHT = 40;
    protected Projectile projectile;
    private int price;
    private boolean upgradeSpeed = false;
    private boolean upgradeRange = false;
    private boolean upgradeDamage = false;
    private List<Minion> inRange = getGame().getMinionsInRange(this);
    private boolean multiple = false;
    private Minion target;

    public Tower(Vector2 center, Game game, float range, float delay, int price) {
        super(game);
        getPosition().setHeight(HEIGHT);
        getPosition().setWidth(WIDTH);
        getPosition().setCenter(center);
        this.range = range;
        this.delay= delay;
        this.price = price;
    }

    public void beMultiple() {
        multiple = true;
    }

    /*
        actualiza el target de la torre
         */

    public void update(float timedelta) {
        timer += timedelta;
        inRange = getGame().getMinionsInRange(this);
        if (timer > delay) {
            if (!inRange.isEmpty()) {
                if (multiple) {
                    attack(inRange);
                } else {
                    target = inRange.get(0);
                    attack(target);
                }
            }
         /*   if(target != null && getDistance(target) > range) {
                target = null;
            }
            if(target == null || target.isKilled()) {
                inRange = getGame().getMinionsInRange(this);

                if (!inRange.isEmpty()) {
                    target = inRange.get(0);
                }
            }
            if(target != null && !target.isKilled()) {
                attack(target);

                if(target.isKilled()) {
                    target = null;
                }
            }*/
            timer = 0;
        }
    }

    public Minion getTarget() {
        return target;
    }

    //    protected void updateMultiple(float timedelta){
//        timer += timedelta;
//        inRange = getGame().getMinionsInRange(this);
//
//        if(timer > delay){
//            if(!inRange.isEmpty()){
//                attack(inRange);
//            }
//        }
//        timer = 0;
//    }


    /*
    está atacando si tiene target y éste no está muerto
     */

    public boolean isAttacking() {
//        return target != null && !target.isKilled();
        return !inRange.isEmpty();
    }

    /*
    devuelve el range
     */
    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

/*    public void upgradeRange(){
        if(!upgradeRange && Player.getMoney() >= 100) {
            upgradeRange = true;
            setRange(getRange() * 2);
            Player.spendMoney(100);
        }
    }
    public void upgradeSpeed(){
        if(!upgradeSpeed && Player.getMoney() >= 200) {
            upgradeSpeed = true;
            setDelay(getDelay() / 2);
            Player.spendMoney(200);
        }
    }
    public void upgradeDamage(){
        if(!upgradeDamage && Player.getMoney() >= 300) {
            upgradeDamage = true;
            projectile.upgradeDamage();
            Player.spendMoney(300);
        }
    }

    */

    /*
    ataca al minion mediante el método damage que tiene éste. Se le pasa un int que es la cantidad de puntos que
    le saca.
     */
    public void attack(Minion minion) {
        minion.damage(projectile);
    }

    public void attack(List<Minion> targets){
        for(Minion m: targets){
            m.damage(projectile);
        }
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public int getPrice(){return price;}
}
