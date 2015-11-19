package tower.defense.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import tower.defense.model.Minion.ElectricMinion;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Minion.MultipleMinion;
import tower.defense.model.Minion.RedMinion;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomi on 18/11/2015.
 */
public class Level {

    private boolean done;
    private Game game;
    private List<Minion> minionList;

    public Level(Game game, int RedMinion, int MultipleMinion, int ElectricMinion){
        this.game = game;
        done = false;
        minionList = new ArrayList<Minion>();
        addRedMinion(RedMinion);
        addMultipleMinion(MultipleMinion);
        addElectricMinion(ElectricMinion);
    }

    public void addRedMinion(int amount){
        for(int i = 0; i<amount;i++){
            minionList.add(new RedMinion(new Vector2(10,75),game, game.getPath() ));
        }
    }

    public void addMultipleMinion(int amount){
        for(int i = 0;i<amount;i++){
            minionList.add(new MultipleMinion(new Vector2(10,75), game, game.getPath()));
        }
    }

    public void addElectricMinion(int amount){
        for(int i = 0;i<amount;i++){
            minionList.add(new ElectricMinion(new Vector2(10,75), game,game.getPath()));
        }
    }

    public List<Minion> getminionList(){
        return minionList;
    }

    public void go(long delaySeconds){
        if(done == false) {
            Timer.schedule(task(), delaySeconds, 0.5f);
            changeDone();
        }
    }

    public Timer.Task task(){
        Timer.Task task = new Timer.Task() {
            int count = 0;

            @Override
            public void run() {
                if(count < minionList.size()) {
                    game.addMinion(minionList.get(count));
                    count++;
                }
            }
        };
        return task;
    }

    public void changeDone(){
        done = true;
    }

    public boolean getDone(){
        return done;
    }
}
