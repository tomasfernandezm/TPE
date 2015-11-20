package tower.defense.model.Minion;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Path;

/**
 * Created by Pato on 11/20/15.
 */
public class BossMinion extends Minion {

    public BossMinion(Vector2 center, Game game, Path path) {
        super(center, game, path, 10000);
    }

    @Override
    public int getType() {
        return 8;
    }
}
