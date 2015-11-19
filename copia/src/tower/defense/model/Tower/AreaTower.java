package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
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
        super(center, game, 100, 1,400);
        beMultiple();
        projectile = new SimpleProjectile(this);
    }

}
