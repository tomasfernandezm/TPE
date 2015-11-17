package tower.defense.model.Minion;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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

    private boolean killed = false;
    private Vector2 velocity;
    private final static float WIDTH = 40;
    private final static float HEIGHT = 40;
    private int hitpoints;
    private Path path;
    private boolean reachEnd = false;
    private boolean electric = false; //si es true, recibe mucho daño de torre electrica y poca de las otras
    private float slowFactor = 0.5f;
    private float slowTimer = 0;
    private boolean slow = false;
    private boolean inX = true;

    public Minion(Vector2 center, Game game, Path path, int hitpoints, Vector2 velocity) {
        super(game);
        this.velocity = velocity;
        this.hitpoints = hitpoints;
        getPosition().setHeight(HEIGHT);
        getPosition().setWidth(WIDTH);
        getPosition().setCenter(center);
        this.path = path;
    }
    public Minion(Vector2 center, Game game, Path Path, int hitpoints){
        this(center, game, Path, hitpoints, new Vector2(2f, 0f));
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
        Vector2 aux = velocity;
        velocity.scl(slowMinion(timedelta));
        move();
        velocity = aux;
        //System.out.println(velocity.epsilonEquals(new Vector2(2f, 0f),1f));
        //System.out.println(velocity);

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
    public boolean isReachEnd(){ return reachEnd;}

    /*
    le quita puntos de vida al minion
     */
    public void damage(Projectile projectile) {
        if(projectile instanceof FreezeRay){
            if(!slow) {
                slowTimer = 0.003f;
                slow = true;
            }
        }else {
            if (electric) {
                if (projectile instanceof ElectricRay) {
                    hitpoints -= projectile.getDamage() * 2;
                } else {
                    hitpoints -= projectile.getDamage() / 2;
                }
            } else {
                hitpoints -= projectile.getDamage();
            }
            if (hitpoints <= 0) {
                die();
            }
        }
        //System.out.println("Being attacked!");
        //System.out.println(velocity.angle(new Vector2(100,100)));
    }

    /*
    nose que hace
     */
    public void death() {
        Minion minion = new RedMinion(getPosition().getCenter(new Vector2(0,400)), getGame(),path);

        if(minion.velocity.x > 0) {
            minion.velocity.x *= -1;
        }
        getGame().addMinion(minion);
    }
    private void changeInX(){
        inX = !inX;
    }

    private void move(){

        Vector2 vect = new Vector2();
        getPosition().getCenter(vect);
        vect.add(velocity);

        for(int i = 0;i<path.getRectangles().size();i++){
            if(this.getPosition().overlaps(path.getRectangles().get(i))){
                // System.out.println("Llego al: " + i);
                if(i==25){
                    velocity.rotate(-90);
                    vect.add(velocity);
                    changeInX();
                }
                if(i==22){
                    velocity.rotate(-90);
                    vect.add(velocity);
                    changeInX();
                }
                if(i==3){
                    velocity.rotate(90);
                    vect.add(velocity);
                    changeInX();
                }
                if(i==10){
                    velocity.rotate(90);
                    vect.add(velocity);
                    changeInX();
                }
                if(i==45){
                    velocity.rotate(90);
                    vect.add(velocity);
                    changeInX();
                }
                if(i==38){
                    velocity.rotate(-90);
                    vect.add(velocity);
                    changeInX();
                }
                if(i==54){
                    reachEnd = true;
                }
            }

        }

        getPosition().setCenter(vect);
    }

    private float slowMinion(float timedelta){
        if(slowTimer <= 0){
            slow = false;
            //          normalVelocity();
            return 1;
        }
        slowTimer -= timedelta;
        return slowFactor;
    }
    protected void beElectric(){electric = true;}

    protected void die(){killed = true;}

    protected Path getPath(){return path;}

    public int getType(){
        if (this instanceof RedMinion){
            return 1;
        }else{
            if (this instanceof MultipleMinion){
                return 2;
            }else{
                return 3;
            }
        }
    }

    public Vector2 getVelocity(){ return velocity;}

    private void normalVelocity(){
        if(inX && !(velocity.epsilonEquals(new Vector2(2f, 0f),1f))){
            velocity = new Vector2(2f, 0f);
        }else{
            if(!inX && !(velocity.epsilonEquals(new Vector2(0f, 2f),1f))){
                velocity = new Vector2(0f, 2f);
            }
        }
    }
}
//if(inX && !(velocity.epsilonEquals(new Vector2(2f, 0f),1f))){
//        velocity = new Vector2(2f, 0f);
//        }else{
//        if(!inX && !(velocity.epsilonEquals(new Vector2(0f, 2f),1f))){
//        velocity = new Vector2(0f, 2f);
//        }
//        }


//falta ver en y cuando baja si es 135 (segundo if)
//    private void slowMinion2(float timedelta){
//        Vector2 aux = velocity;
//        timer += timedelta;
//        if(timer > 2){
//            if((int)velocity.angle(new Vector2(10,10)) == -44) {
//                velocity.add(0f,-0.5f);
//                System.out.println("1");
//            }
//            if((int)velocity.angle(new Vector2(10,10)) == 135) {
//                velocity.add(0f,0.5f);
//                System.out.println("2");
//            }
//            if((int)velocity.angle(new Vector2(10,10)) == 45) {
//                velocity.add(-0.5f,0f);
//                System.out.println("3");
//            }
//            if((int)velocity.angle(new Vector2(10,10)) == -134) {
//                velocity.add(0.5f,0f);
//                System.out.println("4");
//            }
//            timer = 0;
//        }else{
//            velocity = aux;
//        }
//    }