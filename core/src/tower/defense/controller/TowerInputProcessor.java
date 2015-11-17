package tower.defense.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Tower.SimpleTower;
import tower.defense.model.Tower.Tower;
import tower.defense.ui.UIManager;

/**
 * Created by Tomi on 13/11/2015.
 */
public class TowerInputProcessor extends MyInputProcessor implements com.badlogic.gdx.InputProcessor {

    UIManager uiManager;
    tower.defense.model.Game game;

    public TowerInputProcessor(UIManager uiManager, Game game) {
        this.uiManager = uiManager;
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 45) {
            game.addTower(new SimpleTower(new Vector2(100, 300), game));
            System.out.println("Tower added");
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("Dragged");
        System.out.println(screenX + " + " + screenY);
        for (Tower t : game.getTowers()) {
            if (touchDown( (int)t.getPositionX(), (int) t.getPositionY(), 0, 0)) {
                t.getPosition().setPosition(screenX, screenY);
            }
        }
        return true;
    }
}
