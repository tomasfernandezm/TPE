package tower.defense.model.Minion;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Path;

/**
 * Created by Pato on 11/16/15.
 */
public class MultipleMinion extends Minion{
    public MultipleMinion(Vector2 center, Game game, Path path) {
        super(center, game, path,200);
    }

    @Override
    public void die() {
        super.die();
        getGame().addMinion(new RedMinion(getPosition().getCenter(new Vector2(0,400)), this.getGame(), this.getPath(),this.getVelocity()));
    }

    @Override
    public int getType() {
        return 2;
    }
}
