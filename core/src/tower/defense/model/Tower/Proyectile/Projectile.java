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
    private Tower tower;

    public Projectile(float damage, Tower tower){
        this.damage = damage;
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
}
