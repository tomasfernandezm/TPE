package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.Bomb;
import tower.defense.model.Tower.Proyectile.Projectile;
import tower.defense.model.Player;

import java.util.List;

/**
 * Created by Tomi on 08/11/2015.
 */
public abstract class Tower extends Entity {
    protected float range;
    protected float delay;
    protected float timer = 0;
    private final static float WIDTH = 40;
    private final static float HEIGHT = 40;
    private int price;
    private boolean upgradeSpeed = false;
    private boolean upgradeRange = false;
    private boolean upgradeDamage = false;
    protected float damageFactor = 1;
    protected List<Minion> inRange = getGame().getMinionsInRange(this);



    public Tower(Vector2 center, Game game, float range, float delay, int price) {
        super(game);
        getPosition().setHeight(HEIGHT);
        getPosition().setWidth(WIDTH);
        getPosition().setCenter(center);
        this.range = range;
        this.delay= delay;
        this.price = price;
    }

    public abstract void update(float timedelta);

    public boolean isAttacking() {
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

    public void upgradeRange(){
        if(!upgradeRange && getGame().getPlayer().getMoney() >= 100) {
            upgradeRange = true;
            setRange(getRange() * 1.15f);
            getGame().getPlayer().spendMoney(100);
        }
    }
    public void upgradeSpeed(){
        if(!upgradeSpeed && getGame().getPlayer().getMoney() >= 200) {
            upgradeSpeed = true;
            setDelay(getDelay() / 1.25f);
            getGame().getPlayer().spendMoney(200);
        }
    }
    public void upgradeDamage(){
        if(!upgradeDamage && getGame().getPlayer().getMoney() >= 300) {
            upgradeDamage = true;
            damageFactor = 1.25f;
            getGame().getPlayer().spendMoney(300);
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
