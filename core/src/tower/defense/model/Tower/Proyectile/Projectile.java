package tower.defense.model.Tower.Proyectile;

/**
 * Created by Tomi on 08/11/2015.
 */
public abstract class Projectile {

    protected int damage;

    public Projectile(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}
