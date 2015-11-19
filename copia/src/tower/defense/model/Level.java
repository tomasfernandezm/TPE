package tower.defense.model;

import tower.defense.model.Minion.Minion;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by tomifor on 17/11/15.
 */
public class Level {
    List<Minion> listMinion;
    Game game;

    public Level(Game game){
        this.game=game;
        //listMinion = new List<Minion>();
    }


}
