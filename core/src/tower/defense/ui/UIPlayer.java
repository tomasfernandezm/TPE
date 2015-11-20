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
        bitmapFont.draw(uiManager.getBatch(),stringMoney(player.getMoney()), 525, 350);
        bitmapFont.draw(uiManager.getBatch(),stringScore(player.getScore()), 525, 300 );
        bitmapFont.draw(uiManager.getBatch(),stringLevel(player.getLevel()), 525, 250);
        bitmapFont.draw(uiManager.getBatch(),stringLives(player.getLives()), 525, 200);

        if(player.isOver()){
            bitmapFont.draw(uiManager.getBatch(),"GAME OVER", 300, 200);
        }
        uiManager.getBatch().end();

    }

    public String stringMoney(int money){
        String a = "Money: " + money;
        return a;
    }

    public String stringScore(double score){
        String a = "Score: " + score;
        return a;
    }

    public String stringLevel(int level){
        String a = "Level: "+ level;
        return a;
    }

    public String stringLives(int lives){
        String a = "Lives: " + lives;
        return a;
    }
}
