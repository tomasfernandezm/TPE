package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Tower.Proyectile.Bomb;


/**
 * Created by Tomi on 08/11/2015.
 */
/*
tira bombas, falta terminar
 */
public class BomberTower extends Tower {

    public BomberTower(Vector2 center, Game game){
        super(center, game, 150, 5, new Bomb());
    }

    @Override
    public void update(float timedelta) {
        updateSimple(timedelta);
    }
}
