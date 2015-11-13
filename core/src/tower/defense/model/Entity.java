package tower.defense.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tomi on 08/11/2015.
 */

/*
 clase padre de torres y minions, es un ente del juego cualquiera
*/
public abstract class Entity {

    /*
     cada entidad tiene un rectángulo como posición y un game al cuál pertenece
    */
    private Rectangle position = new Rectangle();
    private Game game;

    /*
    Constructor
     */
    public Entity(Game game) {
        this.game = game;
    }

    /*
     devuelve el game al que pertenece
      */
    protected Game getGame() {
        return this.game;
    }


    public abstract void update(float timedelta);

    /*
     devuelve posición
      */
    public Rectangle getPosition() {
        return position;
    }

    /*
    devuelve la distancia entre este entity y otro que se le pasa como argumento
     */
    public float getDistance(Entity entity) {
        Vector2 own = this.getPosition().getCenter(new Vector2());
        Vector2 other = entity.getPosition().getCenter(new Vector2());

        return own.dst(other);
    }

    public float getPositionX(){
        return position.getX();
    }

    public float getPositionY(){
        return position.getY();
    }

}
