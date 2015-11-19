package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.FreezeRay;
import tower.defense.model.Tower.Proyectile.Projectile;

import java.util.List;


/**
 * Created by Tomi on 08/11/2015.
 */

/*
congela a los enemigos, falta terminar
 */
public class FreezeTower extends Tower {

    public FreezeTower(Vector2 center, Game game){
        super(center, game, 100, 3,400);
    }

    @Override
    public void update(float timedelta) {
        timer += timedelta;
        inRange = getGame().getMinionsInRange(this);
        if (timer > delay) {
            if (!inRange.isEmpty()) {
                for(Minion target: inRange){
                    if(!target.isSlow()){
                        shoot(target);
                        break;
                    }
                }
        }
        timer = 0;
    }
    }

    public void shoot(Minion target) {
        Projectile projectile =new FreezeRay(target,this,damageFactor);
        getGame().addProjectile(projectile);
    }
}
