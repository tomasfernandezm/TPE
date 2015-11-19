package tower.defense.controller;

/**
 * Created by Tomi on 08/11/2015.
 */
public interface InputProcessor {
        boolean keyDown (int keycode);

        boolean keyUp (int keycode);

        boolean keyTyped (char character);

        boolean mouseMoved (int x, int y);

        boolean scrolled (int amount);
}
