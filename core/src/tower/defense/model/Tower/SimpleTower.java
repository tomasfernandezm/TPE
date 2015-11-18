package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Tower.Proyectile.SimpleProjectile;

/**
 * Created by Tomi on 08/11/2015.
 */

/*
proyectil de la torre simple, usa pryectil simple, falta terminar
 */
public class SimpleTower extends Tower{

    public SimpleTower(Vector2 center, Game game){
        super(center, game, 100, 1, new SimpleProjectile(),100);
    }

}
