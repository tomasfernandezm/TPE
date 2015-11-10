package tower.defense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import tower.defense.model.Game;
import tower.defense.ui.UIManager;

public class towerdefense extends ApplicationAdapter {
	private Game game;
	private UIManager uiManager;



	@Override
	public void create () {
		uiManager = new UIManager();
		game = new Game(uiManager.getBoundaries());
		game.addGameListeners(uiManager);
		game.init();


	}

	@Override
	public void render () {
		uiManager.draw();

		game.update(Gdx.graphics);



	}
}
