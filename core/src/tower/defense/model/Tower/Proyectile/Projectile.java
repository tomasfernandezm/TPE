package tower.defense.model.Tower.Proyectile;

/**
 * Created by Tomi on 08/11/2015.
 */
public abstract class Projectile {

    protected float damage;

    public Projectile(float damage){
        this.damage = damage;
    }

    public float getDamage(){
        return damage;
    }

    public void setDamage(float damage) {this.damage = damage;}

    public void upgradeDamage(){
        setDamage(getDamage()*2);
    }
}
