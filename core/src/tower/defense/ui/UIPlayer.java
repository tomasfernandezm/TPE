package tower.defense.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import javafx.scene.paint.Color;
import tower.defense.model.Player;

/**
 * Created by Tomi on 08/11/2015.
 */
public class UIPlayer {

    private Player player;
    private BitmapFont bitmapFont;
    private String score;

    public UIPlayer(Player player){
        this.player = player;
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(com.badlogic.gdx.graphics.Color.WHITE);
    }

    public void draw(UIManager uiManager){

        uiManager.getBatch().begin();
        bitmapFont.draw(uiManager.getBatch(),stringMoney(player.getMoney()), 550, 350);
        uiManager.getBatch().end();

    }

    public String stringMoney(int money){
        String a = "Money: " + money;
        return a;
    }
}
