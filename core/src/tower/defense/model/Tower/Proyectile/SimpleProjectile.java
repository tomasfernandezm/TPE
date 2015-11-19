package tower.defense.model.Tower.Proyectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Tower;

/**
 * Created by Tomi on 08/11/2015.
 */
public class SimpleProjectile extends Projectile{

    public SimpleProjectile(Minion target, Tower tower, float damageFactor){
        super(75,target,tower,damageFactor);
    }

    @Override
    public void damage() {
        getTarget().receiveDamage(getDamage());
    }
}
