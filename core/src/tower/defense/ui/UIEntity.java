package tower.defense.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import tower.defense.model.Entity;


/**
 * Created by Tomi on 08/11/2015.
 */

/*
clase encargada de los gráficos de la Entity y recibe una subclase de Entity
 */
public class UIEntity<T extends Entity> {

    private T model;
    private Texture texture;


    public UIEntity(Texture texture, T model){
        this.model = model;
        this.texture = texture;
    }

    public void draw(UIManager uiManager) {
        Rectangle r = getModel().getPosition();

        uiManager.getBatch().begin();
        uiManager.getBatch().draw(texture, r.x, r.y, r.getWidth(), r.getHeight());
        uiManager.getBatch().end();

    }

    public T getModel() {
        return model;
    }


}
