package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.ElectricRay;
import tower.defense.model.Tower.Proyectile.FreezeRay;
import tower.defense.model.Tower.Proyectile.Projectile;


/**
 * Created by Tomi on 08/11/2015.
 */

/*
torre eléctrica, usa rayos, falta terminar.
 */
public class TeslaTower extends Tower {

    public TeslaTower(Vector2 center, Game game){
        super(center, game, 100, 1,800);
    }

    public void update(float timedelta){
        timer += timedelta;
        inRange = getGame().getMinionsInRange(this);
        if(timer > delay){
            if(!inRange.isEmpty()){
                shoot(inRange.get(0));
            }
            timer = 0;
        }
    }

    public void shoot(Minion target){
        Projectile projectile = new FreezeRay(target, this, damageFactor);
        getGame().addProjectile(projectile);
    }

}
