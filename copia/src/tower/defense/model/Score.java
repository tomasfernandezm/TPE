package tower.defense.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by tomifor on 18/11/15.
 */
public class Score {
    private int score;
    private String name;

    public Score (int score, String name){
        Date d = new Date();
        this.score=score;
        this.name= name + " " + String.valueOf(d);
    }

    public Score (int score, String name, String date){
        this.score=score;
        this.name=name;

    }



    public int getScore(){
        return score;
    }


    public String getName(){
        return name;
    }

    public String toString(){
        String s = score+ " " + name;
        return s;
    }
}