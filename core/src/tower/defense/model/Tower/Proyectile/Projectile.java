package tower.defense.model.Tower.Proyectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import tower.defense.model.Entity;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Tower;

/**
 * Created by Tomi on 08/11/2015.
 */
public abstract class Projectile {

    private float damage;
    private Minion target;
    private Tower tower;
    private long time = 1;
    private boolean hit = false;
    protected float damageFactor;

    public void update(float timedelta){

        time -= timedelta;
        if(time <= 0){
            hit = true;
            System.out.println(1);
        }
    }

    public Projectile(float damage, Minion target, Tower tower, float damageFactor) {
        this.damage = damage*damageFactor;
        this.target = target;
        this.tower = tower;
        this.damageFactor = damageFactor;
        damage();
    }

    public float getDamage(){
        return damage;
    }

    public void setDamage(float damage) {this.damage = damage;}

    public void upgradeDamage(){
        setDamage(getDamage()*2);
    }

    public Tower getTower() {
        return tower;
    }

    public Minion getTarget() {
        return target;
    }

    public abstract void damage();

    public boolean hit(){
        return hit;
    }
}
