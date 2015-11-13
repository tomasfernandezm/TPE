package tower.defense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import tower.defense.controller.TowerInputProcessor;
import tower.defense.model.Game;
import tower.defense.ui.UIManager;

public class towerdefense extends ApplicationAdapter {
	private Game game;
	private UIManager uiManager;
	private TowerInputProcessor towerInputProcessor;



	@Override
	public void create () {
		uiManager = new UIManager();
		game = new Game(uiManager.getBoundaries());
		towerInputProcessor = new TowerInputProcessor(uiManager, game);
		game.addGameListeners(uiManager);
		game.init();


	}

	@Override
	public void render () {
		uiManager.draw();
		Gdx.input.setInputProcessor(towerInputProcessor);
		game.update(Gdx.graphics);



	}
}
