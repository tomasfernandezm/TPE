package tower.defense.model;


import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by Tomi on 09/11/2015.
 */
public class Path {

    List<Point2D> pointlist;

    public Path(Point2D ... points){
        for(int i = 0;i<points.length;i++){
            pointlist.add(points[i]);
        }
    }
}
