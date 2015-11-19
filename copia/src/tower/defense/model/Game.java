package tower.defense.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Minion.ElectricMinion;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Minion.MultipleMinion;
import tower.defense.model.Minion.RedMinion;
import tower.defense.model.Tower.AreaTower;
import tower.defense.model.Tower.Proyectile.Bomb;
import tower.defense.model.Tower.Proyectile.Projectile;
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
    private Path path = Path.pathGetInstance();

    private Vector2 boundaries;

    public Game(Vector2 boundaries) {
        this.boundaries = boundaries;
    }

    public void init() {
//        addTower(new SimpleTower(new Vector2(200, 200), this));
        addMinion(new MultipleMinion(new Vector2(25, 75), this, path));
        addMinion(new MultipleMinion(new Vector2(10, 75), this, path));
        addMinion(new MultipleMinion(new Vector2(-25, 75), this, path));
        addMinion(new MultipleMinion(new Vector2(-35, 75), this, path));
        addMinion(new MultipleMinion(new Vector2(-45, 75), this, path));
        addMinion(new MultipleMinion(new Vector2(0, 75), this, path));
    }

    public void update(Graphics graphics) {
        Collection<Minion> minionsToRemove = new HashSet<Minion>();
        for(Tower t: towers) {
            t.update(graphics.getDeltaTime());
        }
        for(Minion m: minions) {
            m.update(graphics.getDeltaTime());
            if (m.isKilled()) {
                player.addMoneyMinionKill(m.getType());
                player.increaseScore(m.getType());
                player.printString();
                minionsToRemove.add(m);
                for (GameListener gl : listeners)
                    gl.minionKilled(m);
            }
            if (m.isReachEnd()) {
                player.spendLife(m.getType());
                minionsToRemove.add(m);
                player.printString();
                for (GameListener gl : listeners)
                    gl.minionKilled(m);
            }
        }
        minions.removeAll(minionsToRemove);
    }

    public Vector2 getBoundaries() {
        return boundaries;
    }

/*    public List<Minion> getMinionsInBlastRange(Projectile projectile) {
        List<Minion> ret = new LinkedList<Minion>();

        for(Minion m:minions) {
            if (tower.getDistance(m) < tower.getRange()) {
                ret.add(m);
            }
        }
        return ret;
    }*/

    public List<Minion> getMinionsInRange(Tower tower) {
        List<Minion> ret = new LinkedList<Minion>();

        for(Minion m:minions) {
            if (tower.getDistance(m) < tower.getRange()) {
                ret.add(m);
            }
        }
        return ret;
    }

    public List<Minion> getMinionsInRange(Bomb bomb) {
        List<Minion> ret = new LinkedList<Minion>();

        for(Minion m:minions) {
            if (bomb.getDistance(m) < bomb.getRange()) {
                ret.add(m);
            }
        }
        return ret;
    }

    public void addTower(Tower tower) {
        if(path.contains(tower) && player.getMoney() >= tower.getPrice()) {
            this.towers.add(tower);
            player.spendMoney(tower.getPrice());
            for (GameListener gl : listeners)
                gl.towerAdded(tower);
        }
    }

    public void addMinion(Minion minion) {
        minions.add(minion);
        for(GameListener gl: listeners)
            gl.minionAdded(minion);

    }

    public Collection<Tower> getTowers(){
        return towers;
    }

    public void addGameListeners(GameListener gameListener) {
        listeners.add(gameListener);
    }
}
