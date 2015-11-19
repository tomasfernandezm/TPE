package tower.defense.model.Tower.Proyectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Tower.Tower;

/**
 * Created by Tomi on 08/11/2015.
 */
public class SimpleProjectile extends Projectile{

    public SimpleProjectile(Tower tower){
        super(100,tower);
    }
}
