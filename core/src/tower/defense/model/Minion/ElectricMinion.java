package tower.defense.model.Minion;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Path;

/**
 * Created by Pato on 11/16/15.
 */
public class ElectricMinion extends Minion{
    public ElectricMinion(Vector2 center, Game game, Path path) {
        super(center, game, path,1000);
        beElectric();
    }

    @Override
    public int getType() {
        return 3;
    }
}
