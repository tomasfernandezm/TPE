package tower.defense.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import tower.defense.controller.TextInputListener;
import tower.defense.model.Minion.ElectricMinion;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Minion.MultipleMinion;
import tower.defense.model.Minion.RedMinion;
import tower.defense.model.Tower.AreaTower;
import tower.defense.model.Tower.Proyectile.Bomb;
import tower.defense.model.Tower.Proyectile.Projectile;
import tower.defense.model.Tower.SimpleTower;
import tower.defense.model.Tower.Tower;
import tower.defense.ui.UIManager;
import tower.defense.ui.UIPlayer;

import java.util.*;


/**
 * Created by Tomi on 08/11/2015.
 */
public class Game {
    private List<Tower> towers = new ArrayList<Tower>();
    private Collection<Minion> minions = new HashSet<Minion>();
    private Collection<Minion> minionsToRemove = new HashSet<Minion>();
    private Collection<Minion> minionsToAdd = new HashSet<Minion>();
    private Collection<Projectile> projectiles = new HashSet<Projectile>();
    private Collection<Projectile> projectilesToAdd = new HashSet<Projectile>();
    private Collection<Projectile> projectilesToRemove = new HashSet<Projectile>();

    private List<GameListener> listeners = new LinkedList<GameListener>();

    private Player player = new Player();
    private Path path = Path.pathGetInstance();
    private Levels levels = new Levels(this);
    private boolean gameOver = false;
    private UIPlayer uiPlayer = new UIPlayer(player);

    private Vector2 boundaries;
    private TextInputListener listener = new TextInputListener(player);

    public Game(Vector2 boundaries) {
        this.boundaries = boundaries;
        levels.addLevel(3, 0, 0);
        levels.addLevel(3, 3, 0);
        levels.addLevel(3, 3, 3);
        levels.addLevel(5, 5, 5);
        levels.addLevel(6, 6, 6);

    }
    public void init() {
        Gdx.input.getTextInput(listener, "Enter Name: ", "", "");
        System.out.println(listeners.get(0));
    }

    public void update(Graphics graphics) {
        for(Tower t: towers) {
            t.update(graphics.getDeltaTime());
        }

        for(Projectile p: projectiles){
            p.update(graphics.getDeltaTime());
            if(p.hit()){
                projectilesToRemove.add(p);
            }
        }
        projectiles.removeAll(projectilesToRemove);
        projectilesToRemove.clear();
        projectiles.addAll(projectilesToAdd);
        projectilesToAdd.clear();

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
        minionsToRemove.clear();
        minions.addAll(minionsToAdd);
        minionsToAdd.clear();
        gameOver();

        uiPlayer.draw((UIManager)listeners.get(0));

    }
// probando
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

    public List<Minion> getMinionInRange(Bomb bomb){
        List<Minion> ret = new LinkedList<Minion>();

        for(Minion m:minions){
            if(bomb.getDistance(m) < bomb.getRange()){
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
        minionsToAdd.add(minion);
        for(GameListener gl: listeners)
            gl.minionAdded(minion);

    }

    public void addProjectileToAdd(Projectile projectile){
        projectilesToAdd.add(projectile);
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public void endMinion(){
        minions.removeAll(minions);
    }
    public void gameOver(){
        if(player.isOver() && gameOver==false){
            levels.gameOver();
            Score s = new Score((int)player.getScore(), player.getName());
            SaveScore score = new SaveScore(s);
            System.out.println("GAME OVER");
            endMinion();
            gameOver=true;
        }
    }

    public List<Tower> getTowers(){
        return towers;
    }

    public void addGameListeners(GameListener gameListener) {
        listeners.add(gameListener);
    }

    public Path getPath(){
        return path;
    }

    public Levels getLevels(){
        return levels;
    }

    public Player getPlayer(){
        return player;
    }

}
