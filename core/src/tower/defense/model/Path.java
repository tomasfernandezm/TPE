package tower.defense.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Tower;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomi on 13/11/2015.
 */
public class Path {
    private static Path instance;
    private LinkedList<Rectangle> rectangleList = new LinkedList<Rectangle>();
    ;
    private final int[][] path2 = new int[][]{{9, 1, 9, 9, 9, 9, 9, 9}, {9, 1, 9, 9, 4, 5, 6, 9}, {9, 1, 9, 9, 4, 9, 6, 9}, {9, 1, 9, 9, 4, 9, 6, 9}, {9, 1, 2, 3, 4, 9, 6, 9}, {9, 9, 9, 9, 9, 9, 6, 9}, {9, 9, 9, 9, 9, 9, 6, 9}, {9, 1, 2, 3, 4, 5, 6, 9}, {9, 1, 9, 9, 9, 9, 9, 9}, {9, 1, 9, 9, 9, 9, 9, 9}};
    ;

    public static Path pathGetInstance() {
        if (instance == null) {
            instance = new Path();
        }
        return instance;
    }

    private Path() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (j != path2[i][j]) {
                    addRectangle(new Rectangle(i * 50, j * 50, 50, 50));
                }
            }
        }
    }

    public boolean contains(Entity entity) {
        for (Rectangle r : rectangleList) {
            if (r.contains(entity.getPosition().getCenter(new Vector2()))) {
                return true;
            }
        }
        return false;
    }


    public void addRectangle(Rectangle rectangle) {
        rectangleList.add(rectangle);
    }

    public LinkedList<Rectangle> getRectangles() {
        return rectangleList;
    }

    public int[][] getPath() {
        return path2;
    }

    private long time;
    private boolean inX;

    public int rotate(Minion minion) {
        if(TimeUtils.nanoTime() - time > 2000000000) {
            for (Rectangle r : rectangleList) {
//            if(minion.getPosition().overlaps(r)){
                if (minion.isInX()) {
                    //abajo
                    if ((r.contains(new Vector2(minion.getPositionX() + 46, minion.getPositionY())) || r.contains(new Vector2(minion.getPositionX() - 46, minion.getPositionY())) && (minion.getVelocity().angle() > -0.1 && minion.getVelocity().angle() < 0.1))) {
                        if (r.contains(new Vector2(minion.getPositionX(), minion.getPositionY() + 70))) {
//                            minion.setVelocity(minion.getVelocity().setAngle(270));
                            time = TimeUtils.nanoTime();
                            minion.changeInX();
                            System.out.println(1);
                            return 1;

                        } else {
                            //arriba
//                            minion.setVelocity(minion.getVelocity().setAngle(90));
                            time = TimeUtils.nanoTime();
                            minion.changeInX();
                            System.out.println(2);
                            return 2;
                        }
                    }
                } else {
                    if (r.contains(new Vector2(minion.getPositionX(), minion.getPositionY() + 46)) || r.contains(new Vector2(minion.getPositionX(), minion.getPositionY() - 46))) {
                        //izquierda
                        if (r.contains(new Vector2(minion.getPositionX() + 70, minion.getPositionY()))) {
//                            minion.setVelocity(minion.getVelocity().setAngle(180));
                            time = TimeUtils.nanoTime();
                            minion.changeInX();
                            System.out.println(3);
                            return 3;
                            //derecha
                        } else {
//                            minion.setVelocity(minion.getVelocity().setAngle(0));
                            time = TimeUtils.nanoTime();
                            minion.changeInX();
                            System.out.println(4);
                            return 4;
                        }
                    }
                }

            }
        }
//        minion.getPosition().setCenter(vect);
        return 0;
    }
}