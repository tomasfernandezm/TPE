package tower.defense.model.Minion;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Path;
/**
 * Created by Tomi on 08/11/2015.
 */
public class RedMinion extends Minion{

    public RedMinion(Vector2 center, Game game,Path path) {
        super(center, game, path, 200);
    }

    public RedMinion(Vector2 center, Game game, Path path, Vector2 velocity) {
        super(center, game, path, 200, velocity);
    }

}
