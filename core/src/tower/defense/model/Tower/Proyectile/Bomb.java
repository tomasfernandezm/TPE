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

    private double range;
    private List<Minion> inRange = getTower().getGame().getMinionsInRange(getTower());

    public Bomb(Tower tower){
        super(10,tower);
        range = 100;
    }

    public double getRange(){
        return range;
    }

    public float getDistance(Entity entity) {
        Vector2 own = getTower().getTarget().getPosition().getCenter(new Vector2());
        Vector2 other = entity.getPosition().getCenter(new Vector2());

        return own.dst(other);
    }
}

