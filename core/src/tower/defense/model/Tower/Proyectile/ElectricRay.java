package tower.defense.model.Tower.Proyectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Tower;

/**
 * Created by Tomi on 08/11/2015.
 */
public class ElectricRay extends Projectile {

    public ElectricRay(Minion target, Tower tower, float damageFactor) {
        super(100, target, tower, damageFactor);
    }

    public void damage() {
        if (getTarget().isElectric()) {
            getTarget().recieveDamage(getDamage() * 5);
        } else {
            getTarget().recieveDamage(getDamage() / 2);
        }
    }
}
