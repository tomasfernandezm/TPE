package tower.defense.model.Minion;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import tower.defense.model.Entity;
import tower.defense.model.Game;
import tower.defense.model.Path;
import tower.defense.model.Tower.Proyectile.ElectricRay;
import tower.defense.model.Tower.Proyectile.FreezeRay;
import tower.defense.model.Tower.Proyectile.Projectile;


/**
 * Created by Tomi on 08/11/2015.
 */
public abstract class Minion extends Entity {

    private final static float WIDTH = 40;
    private final static float HEIGHT = 40;
    private boolean killed = false;
    private boolean reachEnd = false;
    private boolean electric = false; //si es true, recibe mucho daño de torre electrica y poca de las otras
    private boolean slow = false;
    private boolean inX = true;
    private Vector2 velocity;
    private float hitpoints;
    private float slowTime;
    private Path path;
    private float slowFactor;

    private int velocityMagnitude = 2;

    public Minion(Vector2 center, Game game, Path path, float hitpoints, Vector2 velocity) {
        super(game);
        this.velocity = velocity;
        this.hitpoints = hitpoints;
        getPosition().setHeight(HEIGHT);
        getPosition().setWidth(WIDTH);
        getPosition().setCenter(center);
        this.path = path;
    }

    public Minion(Vector2 center, Game game, Path Path, int hitpoints) {
        this(center, game, Path, hitpoints, new Vector2(1f, 0f));
    }


    @Override
    public void update(float timedelta) {
        if (isKilled()) {
            return;
        }
        if(getPosition().getCenter(new Vector2()).x > 475) {
            reachEnd = true;
        }
        checkLife();
        if(slow){
            if(TimeUtils.nanoTime() - slowTime > 999999999){
                normalVelocity();
            }
        }
        move();

    }

    public boolean isElectric() {
        return electric;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    protected void beElectric() {
        electric = true;
    }

    protected void die() {
        killed = true;
    }

    protected Path getPath() {
        return path;
    }

    /*
        devuelve true o false si el minion está muerto o llega al final
         */
    public boolean isKilled(){
        return killed;
    }

    public void checkLife() {
        if(hitpoints <= 0){
            die();
        }
    }

    public boolean isReachEnd() {
        return reachEnd;
    }

    public void slow(float slowFactor) {
        this.slowFactor = slowFactor;
        velocity.scl(slowFactor);
        slowTime = TimeUtils.nanoTime();
        slow = true;
    }

    public void normalVelocity(){
        velocity.scl(1 / slowFactor);
        slowTime = 0;
        slow = false;
    }

    public void changeInX() {
        inX = !inX;
    }

    public boolean isInX() {
        return inX;
    }

    private Vector2 velocity() {
        return velocity.scl(velocityMagnitude);
    }

    private void move() {


        Vector2 vect = new Vector2();
        getPosition().getCenter(vect);
        vect.add(velocity);
/*        int rotate = path.rotate(this);

        if (rotate == 1){
            setVelocity(getVelocity().setAngle(270));
        }
        if (rotate == 2){
            setVelocity(getVelocity().setAngle(90));
        }
        if (rotate == 3){
            setVelocity(getVelocity().setAngle(180));
        }
        if (rotate == 4){
            setVelocity(getVelocity().setAngle(0));
        }
        vect.add(velocity);
        getPosition().setCenter(vect);*/


        for (int i = 0; i < path.getRectangles().size(); i++) {
            if (this.getPosition().overlaps(path.getRectangles().get(i))) {
                if (i == 25 || i == 22 || i == 38) {
                    velocity.rotate(-90);
                    vect.add(velocity);
                    changeInX();
                }
                if (i == 3 || i == 10 || i == 45) {
                    velocity.rotate(90);
                    vect.add(velocity);
                    changeInX();
                }
            }

        }

        getPosition().setCenter(vect);
    }

    public abstract int getType();

    public Vector2 getVelocity() {
        return velocity;
    }

    public void receiveDamage(float damage) {
        hitpoints -= damage;
    }
}
