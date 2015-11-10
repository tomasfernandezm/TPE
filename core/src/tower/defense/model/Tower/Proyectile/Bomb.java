package tower.defense.model.Tower.Proyectile;

/**
 * Created by Tomi on 08/11/2015.
 */

/*
proyectil del cañon, falta terminar
 */
public class Bomb extends Projectile {

    private double range;

    public Bomb(){
        damage = 10;
        range = 10;
    }

    public double getRange(){
        return range;
    }
}
