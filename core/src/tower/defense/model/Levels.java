package tower.defense.model;


import com.badlogic.gdx.utils.*;

import java.util.*;
import java.util.Timer;
import java.util.function.Consumer;

/**
 * Created by tomifor on 17/11/15.
 */
public class Levels implements Iterable<Level>{

    private List<Level> levels;
    private Game game;
    private int levelcount;

    public Levels(Game game){
        levels = new ArrayList<Level>();
        this.game = game;
        levelcount = 0;

    }

    public void addLevel(int redminion, int multipleminion, int electricminion, int bossminion){
        levels.add(new Level(game, redminion, multipleminion, electricminion, bossminion));
    }

    public void go(){
        if(levelcount < size()) {
            levels.get(levelcount).go(0);
            levelcount++;
            game.getPlayer().upLevel();
        }
    }

    public void gameOver(){
        for(int i = 0;i<size();i++){
            levels.get(i).changeDone();
        }
    }

    public int size(){
        return levels.size();
    }

    public Iterator<Level> iterator(){
        return new LevelIterator();
    }

    public class LevelIterator implements Iterator<Level>{

        int index = 0;
        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Level next() {
            if(!hasNext()){
                throw new UnsupportedOperationException();
            }else{
                index++;
                return levels.get(index);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

