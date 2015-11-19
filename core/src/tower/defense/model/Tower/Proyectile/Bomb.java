package tower.defense.model.Tower.Proyectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Tower;

import java.util.List;

/**
 * Created by tomifor on 17/11/15.
 */
public class Bomb extends Projectile {

    private static double range = 30;
    private List<Minion> inRange;

    public Bomb(Minion target, Tower tower, float damageFactor){
        super(100,target,tower, damageFactor);
    }

    @Override
    public void damage() {
        inRange = getTarget().getGame().getMinionInRange(this);
        for(Minion target: inRange){
            System.out.println(inRange);
            Projectile projectile = new SimpleProjectile(target, getTower(),damageFactor);
            getTower().getGame().addProjectileToAdd(projectile);
        }
        getTarget().receiveDamage(getDamage());
    }

    public double getRange(){
        return range;
    }

    public List<Minion> getInRange() {return inRange;}

    public float getDistance(Entity entity) {
        Vector2 target = getTarget().getPosition().getCenter(new Vector2());
        Vector2 other = entity.getPosition().getCenter(new Vector2());
        return target.dst(other);
    }
}

