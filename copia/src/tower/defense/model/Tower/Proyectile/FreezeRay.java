package tower.defense.model.Tower.Proyectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Tower.Tower;

/**
 * Created by Tomi on 08/11/2015.
 */
public class FreezeRay extends Projectile{
    private float slowTimer;

    public FreezeRay(Tower tower){
        super(0.5f,tower);
        slowTimer = 1f;

    }

    public float getSlowtimer() {
        return slowTimer;
    }

    public void setSlowtimer(float slowTimer) {
        this.slowTimer = slowTimer;
    }

    @Override
    public void upgradeDamage() {
        return;
    }
}
