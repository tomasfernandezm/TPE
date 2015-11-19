package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.Bomb;
import tower.defense.model.Tower.Proyectile.Projectile;


/**
 * Created by Tomi on 08/11/2015.
 */
/*
tira bombas, falta terminar
 */
public class BomberTower extends Tower {

    public BomberTower(Vector2 center, Game game){
        super(center, game, 150, 4,600);

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
        Projectile projectile = new Bomb(target, this, damageFactor);
        getGame().addProjectile(projectile);
    }

}
