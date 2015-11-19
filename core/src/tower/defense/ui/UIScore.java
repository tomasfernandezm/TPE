package tower.defense.ui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import tower.defense.model.Game;
import tower.defense.model.SaveScore;

/**
 * Created by Tomi on 19/11/2015.
 */
public class UIScore {

    private SaveScore scores;
    private BitmapFont bitmapFont;
    private Game game;

    public UIScore(Game game){
        this.game = game;
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.RED);
    }

    public void draw(UIManager uiManager){
        uiManager.getBatch().begin();
        uiManager.getBatch().end();
    }
}
