package tower.defense.model;

import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomi on 13/11/2015.
 */
public class Path {
    private static Path instance;
    private  LinkedList<Rectangle> rectangleList = new LinkedList<Rectangle>();;
    private final int[][] path2 = new int[][] {{9,1,9,9,9,9,9,9},{9,1,9,9,4,5,6,9},{9,1,9,9,4,9,6,9},{9,1,9,9,4,9,6,9},{9,1,2,3,4,9,6,9},{9,9,9,9,9,9,6,9},{9,9,9,9,9,9,6,9},{9,1,2,3,4,5,6,9},{9,1,9,9,9,9,9,9},{9,1,9,9,9,9,9,9}};;

    public static Path pathGetInstance() {
        if(instance == null) {
            instance = new Path();
        }
        return instance;
    }
    private Path(){
        for(int i=0; i<10; i++){
            for(int j=0; j<8; j++){
                if(j!=path2[i][j]){
                    addRectangle(new Rectangle(i*50,j*50,50,50));
                }
            }
        }
        addRectangle(new Rectangle(500,0,100,400));
    }

    public void addRectangle(Rectangle rectangle) {
        rectangleList.add(rectangle);
    }

    public LinkedList<Rectangle> getRectangles(){
        return rectangleList;
    }

    public int[][] getPath(){return path2;}
}
