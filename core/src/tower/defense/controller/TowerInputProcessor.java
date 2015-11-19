package tower.defense.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Game;
import tower.defense.model.Player;
import tower.defense.model.Tower.*;
import tower.defense.ui.UIManager;
import tower.defense.ui.UIPlayer;
import tower.defense.ui.UITowerEntity;

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
        int levelcount = 0;

        if (keycode == 45) {
            game.addTower(new SimpleTower(new Vector2(Gdx.input.getX(), 500-Gdx.input.getY()), game));
            System.out.println("Simple Tower added");
        }
        if (keycode == 51){
            game.addTower(new AreaTower(new Vector2(Gdx.input.getX(), 500-Gdx.input.getY()), game));
            System.out.println("Area Tower added");
        }
        if (keycode == 33){
            game.addTower(new BomberTower(new Vector2(Gdx.input.getX(), 500-Gdx.input.getY()), game));
            System.out.println("Bomber Tower added");
        }
        if (keycode == 46){
            game.addTower(new FreezeTower(new Vector2(Gdx.input.getX(), 500-Gdx.input.getY()), game));
            System.out.println("Freeze Tower added");
        }
        if (keycode == 48){
            game.addTower(new TeslaTower(new Vector2(Gdx.input.getX(), 500-Gdx.input.getY()), game));
            System.out.println("Tesla Tower added");
        }
        if (keycode == 66){
            game.getLevels().go();
        }
        if (keycode == 8){
            for(int i = 0;i<game.getTowers().size();i++){
                if(game.getTowers().get(i).getPosition().contains(Gdx.input.getX(), 500-Gdx.input.getY())){
                    game.getTowers().get(i).upgradeRange();
                    uiManager.getEntity(game.getTowers().get(i)).upgradeRange(uiManager);
                    System.out.println("Upgrade Range");
                }
            }
        }
        if (keycode == 9){
            for(int i = 0;i<game.getTowers().size();i++){
                if(game.getTowers().get(i).getPosition().contains(Gdx.input.getX(), 500-Gdx.input.getY())){
                    game.getTowers().get(i).upgradeDamage();
                    System.out.println("Upgrade Damage");
                }
            }
        }
        if (keycode == 10){
            for(int i = 0;i<game.getTowers().size();i++){
                if(game.getTowers().get(i).getPosition().contains(Gdx.input.getX(), 500-Gdx.input.getY())){
                    game.getTowers().get(i).upgradeSpeed();
                    System.out.println("Upgrade Speed");
                }
            }
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
