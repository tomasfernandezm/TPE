package tower.defense.controller;

import com.badlogic.gdx.Input;
import tower.defense.model.Player;

/**
 * Created by Tomi on 19/11/2015.
 */
public class TextInputListener implements Input.TextInputListener {

    private Player player;

    public TextInputListener(Player player){
        this.player = player;
    }


    @Override
    public void input(String text) {
        player.setName(text);
    }

    @Override
    public void canceled() {

    }
}
