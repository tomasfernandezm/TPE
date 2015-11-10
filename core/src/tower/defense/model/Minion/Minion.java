package tower.defense.model.Minion;

import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.Game;
import tower.defense.model.Path;

/**
 * Created by Tomi on 08/11/2015.
 */
public abstract class Minion extends Entity {

    private boolean killed = false;
    private Vector2 velocity;
    private final static float WIDTH = 32;
    private final static float HEIGHT = 32;
    private int hitpoints = 100;
    private Path path;

    public Minion(Vector2 center, Game game) {
        super(game);
        getPosition().setHeight(HEIGHT);
        getPosition().setWidth(WIDTH);
        getPosition().setCenter(center);
        //dirección por la cual va el minion
        this.velocity = new Vector2(1f, 0f);

    }

    /*hace al movimiento del minion, se le pasa un timedelta. Se crea un vector, se setea la posición en el vector
    y despues se añade el vector velocity. A continuación, se chequean los límites para ver si esta dentro de la pantalla
    y se setea el centro al nuevo vector VECT.
     */
    @Override
    public void update(float timedelta) {
        if (isKilled()) {
            return;
        }
        Vector2 vect = new Vector2();
        getPosition().getCenter(vect);

        vect.add(velocity);

        checkBoundaries(vect);

        getPosition().setCenter(vect);
    }

    /*
    chequea si el minion está dentro de los límites del juego
     */
    private void checkBoundaries(Vector2 vect) {
        Vector2 bounds = getGame().getBoundaries();

        if(vect.x > bounds.x) {
            vect.x = 0;
        }
        if(vect.x < 0) {
            vect.x = bounds.x;
        }
        if(vect.y > bounds.y) {
            vect.y = 0;
        }
        if(vect.y < 0) {
            vect.y = bounds.y;
        }
    }

    /*
    devuelve true o false si el minion está muerto
     */
    public boolean isKilled() {
        return killed;
    }

    /*
    le quita puntos de vida al minion
     */
    public void damage(int damage) {
        hitpoints = hitpoints-damage;
        if(hitpoints <= 0) {
           killed = true;
        }
        System.out.println("Being attacked!");
    }

    /*
    nose que hace
     */
    public void death() {
        Minion minion = new RedMinion(getPosition().getCenter(new Vector2()), getGame());

        if(minion.velocity.x > 0) {
            minion.velocity.x *= -1;
        }
        getGame().addMinion(minion);
    }
}
