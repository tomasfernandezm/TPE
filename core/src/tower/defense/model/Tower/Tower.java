package tower.defense.model.Tower;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.Game;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Proyectile.Projectile;

import java.util.List;

/**
 * Created by Tomi on 08/11/2015.
 */
public class Tower extends Entity {
    private float range;
    private float delay;
    private float timer = 0;
    private final static float WIDTH = 20;
    private final static float HEIGHT = 37;
    protected Projectile projectile;
    private int upgradeSpeed = 0;
    private int upgradeRange = 0;
    private int upgradeDamage = 0;
    private Minion target;

    public Tower(Vector2 center, Game game, float range, float delay, Projectile projectile) {
        super(game);
        getPosition().setHeight(HEIGHT);
        getPosition().setWidth(WIDTH);
        getPosition().setCenter(center);
        this.range = range;
        this.delay= delay;
        this.projectile = projectile;
    }

    /*
    actualiza el target de la torre
     */

    @Override
    public void update(float timedelta) {
        timer += timedelta;

        /*
        si el timer es mayor al delay y tiene un target pero la distancia entre el target y la torre es
        mayor al rango de la torre entonces vuelve a setear el target en null
         */
        if(timer > delay) {
            if(target != null && getDistance(target) > range) {
                target = null;
            }


            /*
            si no tiene target o el target está muerto, busca en la lista de Minions uno que esté en rango
            si la lista no está vacía, entonces agarra el primero.
             */
            if(target == null || target.isKilled()) {
                List<Minion> inRange = getGame().getMinionsInRange(this);

                if (!inRange.isEmpty()) {
                    target = inRange.get(0);
                }
            }

            /*
            si hay target y no está muerto, entonces ataca, si después de atacar etá muerto,
            entonces lo vuelve a null.
             */
            if(target != null && !target.isKilled()) {
                attack(target);

                if(target.isKilled()) {
                    target = null;
                }

            }
            /*
            vuelve el timer a 0
             */
            timer = 0;
        }

    }

    /*
    está atacando si tiene target y éste no está muerto
     */

    public boolean isAttacking() {
        return target != null && !target.isKilled();
    }

    /*
    devuelve el range
     */
    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public void upgradeRange(){
        upgradeRange++;
        setRange(getRange()*2);
    }

    /*
    ataca al minion mediante el método damage que tiene éste. Se le pasa un int que es la cantidad de puntos que
    le saca.
     */
    public void attack(Minion minion) {
        minion.damage(projectile);
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }
}
