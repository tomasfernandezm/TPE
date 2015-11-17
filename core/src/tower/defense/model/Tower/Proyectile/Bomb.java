package tower.defense.model.Tower.Proyectile;

/**
 * Created by tomifor on 17/11/15.
 */
public class Bomb extends Projectile {

    private double range;

    public Bomb(){
        super(10);
        range = 10;
    }

    public double getRange(){
        return range;
    }
}

