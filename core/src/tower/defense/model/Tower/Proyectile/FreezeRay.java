package tower.defense.model.Tower.Proyectile;

/**
 * Created by Tomi on 08/11/2015.
 */
public class FreezeRay extends Projectile{
    private float slowTimer;

    public FreezeRay(){
        super(0.85f);
        slowTimer = 3f;
    }

    public float getSlowtimer() {
        return slowTimer;
    }

    public void setSlowtimer(float slowTimer) {
        this.slowTimer = slowTimer;
    }

    @Override
    public void upgradeDamage() {
        return;
    }
}
