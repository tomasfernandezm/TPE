package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.Projectile;
import tower.defense.model.Tower.Proyectile.SimpleProjectile;

/**
 * Created by Tomi on 08/11/2015.
 */

/*
proyectil de la torre simple, usa pryectil simple, falta terminar
 */
public class SimpleTower extends Tower{

    public SimpleTower(Vector2 center, Game game){
        super(center, game, 75, 1,150);
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
        Projectile projectile = new SimpleProjectile(target, this, damageFactor);
        getGame().addProjectile(projectile);
    }

}
