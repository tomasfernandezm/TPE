package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Tower.Proyectile.ElectricRay;


/**
 * Created by Tomi on 08/11/2015.
 */

/*
torre eléctrica, usa rayos, falta terminar.
 */
public class TeslaTower extends Tower {

    public TeslaTower(Vector2 center, Game game){
        super(center, game, 100, 1, new ElectricRay(),400);
    }

}
