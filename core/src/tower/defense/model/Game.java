package tower.defense.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Minion.RedMinion;
import tower.defense.model.Tower.SimpleTower;
import tower.defense.model.Tower.Tower;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomi on 08/11/2015.
 */
public class Game {
    private Collection<Tower> towers = new HashSet<Tower>();
    private Collection<Minion> minions = new HashSet<Minion>();

    private List<GameListener> listeners = new LinkedList<GameListener>();

    private Player player = new Player();

    private Vector2 boundaries;

    public Game(Vector2 boundaries) {
        this.boundaries = boundaries;
    }

    public void init() {
        addTower(new SimpleTower(new Vector2(200, 200), this));
        addMinion(new RedMinion(new Vector2(100, 120), this));
    }

    public void update(Graphics graphics) {
        for(Tower t: towers) {
            t.update(graphics.getDeltaTime());
        }
        for(Minion m: minions) {
            m.update(graphics.getDeltaTime());
            if (m.isKilled()) {
                minions.remove(m);
                player.addMoney(50);
                for (GameListener gl: listeners)
                    gl.minionKilled(m);
            }
        }
        if (Gdx.input.isKeyPressed(45)){
            addTower(new SimpleTower(new Vector2(100,300), this));
        }
    }

    public Vector2 getBoundaries() {
        return boundaries;
    }

    public List<Minion> getMinionsInRange(Tower tower) {
        List<Minion> ret = new LinkedList<Minion>();

        for(Minion m:minions) {
            if (tower.getDistance(m) < tower.getRange()) {
                ret.add(m);
            }
        }
        return ret;
    }

    public void addTower(Tower tower) {
        this.towers.add(tower);
        for(GameListener gl: listeners)
            gl.towerAdded(tower);
    }

    public void addMinion(Minion minion) {
        minions.add(minion);
        for(GameListener gl: listeners)
            gl.minionAdded(minion);

    }

    public void addGameListeners(GameListener gameListener) {
        listeners.add(gameListener);
    }

}
