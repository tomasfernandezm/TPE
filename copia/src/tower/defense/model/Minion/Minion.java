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
    private int hitpoints;
    private int collisions = 0;
    private Path path;
    private float slowFactor;
    private int velocityMagnitude = 2;
    private float slowTimer;

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
        this(center, game, Path, hitpoints, new Vector2(1f, 0f));
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
        move();
        if(time != 0){
            time -= timedelta;
            if(time <= 0){
                time =0;
                slow = false;
                velocity.scl(1/slowFactor);

            }
        }

    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /*
        devuelve true o false si el minion está muerto o llega al final
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
                slowTimer = ((FreezeRay) projectile).getSlowtimer();
                slowFactor = projectile.getDamage();
                slow = true;
                slow();
            }
        }else {
            if (electric) {
                if (projectile instanceof ElectricRay) {
                    hitpoints -= projectile.getDamage() * 2;
                } else {
                    hitpoints -= projectile.getDamage() / 4;
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
    private float time;
    private void slow(){
        velocity.scl(slowFactor);
        time = slowTimer;
    }

    public void changeInX(){
        inX = !inX;
    }

    public boolean isInX() {
        return inX;
    }

    private Vector2 velocity(){
       return velocity.scl(velocityMagnitude);
    }
    private void move(){


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


        for(int i = 0;i<path.getRectangles().size();i++){
            if(this.getPosition().overlaps(path.getRectangles().get(i))){
                if(i==25 || i==22 || i == 38){
                    velocity.rotate(-90);
                    vect.add(velocity);
                    changeInX();
                    collisions++;
                }
                if(i==3 || i==10 || i==45){
                    velocity.rotate(90);
                    vect.add(velocity);
                    changeInX();
                    collisions++;
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
//            normalVelocity();
            return 1;
        }
        slowTimer = slowTimer - timedelta;
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
//        float x = 2/(velocity.x + velocity.y);
//        velocity.scl(x);
                if(inX){
            if((collisions == 0 || collisions == 12 || collisions == 18) && !(velocity.epsilonEquals(new Vector2(2f, 0f),1f))){
                velocity = new Vector2(2f, 0f);
            }else{
                if (collisions == 6 && !(velocity.epsilonEquals(new Vector2(-2f, 0f),1f))){
                    velocity = new Vector2(-2f, 0f);
                }
            }
        }else{
            if((collisions == 3 || collisions == 9) && !(velocity.epsilonEquals(new Vector2(0f, 2f),1f))){
                velocity = new Vector2(0f, 2f);
            }else{
                if(collisions == 15 && !(velocity.epsilonEquals(new Vector2(0f, -2f),1f))){
                    velocity = new Vector2(0f, -2f);
                }
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