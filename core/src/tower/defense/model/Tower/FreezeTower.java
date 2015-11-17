package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Tower.Proyectile.FreezeRay;


/**
 * Created by Tomi on 08/11/2015.
 */

/*
congela a los enemigos, falta terminar
 */
public class FreezeTower extends Tower {

    public FreezeTower(Vector2 center, Game game){
        super(center, game, 100, 3, new FreezeRay());
    }
}
