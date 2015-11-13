package tower.defense.model;

import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomi on 13/11/2015.
 */
public class Path {

    //Rectangle rectangle = new Rectangle(200,100, 50, 50);
    //Rectangle rectangle1 = new Rectangle(200,400, 50, 50);

    LinkedList<Rectangle> rectangleList;

    public Path(){
        rectangleList = new LinkedList<Rectangle>();
        addRectangle(new Rectangle(200,100,50,50));
        addRectangle(new Rectangle(180,400,50,50));
        addRectangle(new Rectangle(500,350,50,50));
        addRectangle(new Rectangle(480,100,50,50));
    }

    public void addRectangle(Rectangle rectangle) {
        rectangleList.add(rectangle);
    }

    public LinkedList<Rectangle> getRectangles(){
        return rectangleList;
    }
}
