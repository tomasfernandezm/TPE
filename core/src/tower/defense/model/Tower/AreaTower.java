package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.Projectile;
import tower.defense.model.Tower.Proyectile.SimpleProjectile;

import java.util.List;

/**
 * Created by Tomi on 08/11/2015.
 */

/*
torre que ataca a varios enemigos a la vez, falta terminar, usa proyectil simple
 */
public class AreaTower extends Tower {

    public AreaTower(Vector2 center, Game game) {
        super(center, game, 75, 3,300);
    }

    public void update(float timedelta){
        timer += timedelta;
        inRange = getGame().getMinionsInRange(this);

        if(timer > delay){
            if(!inRange.isEmpty()){
                shoot(inRange);
            }
            timer = 0;
        }
    }

    public void shoot(List<Minion> targets){
        for(Minion target: targets){
            Projectile projectile = new SimpleProjectile(target, this, damageFactor);
            getGame().addProjectile(projectile);
        }
    }

}
