package tower.defense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.GameListener;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Player;
import tower.defense.model.Tower.Tower;

import java.util.HashMap;

/**
 * Created by Tomi on 08/11/2015.
 */
public class UIManager implements GameListener {

    /*
    creador de formas
     */
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    /*
    imagenes de las entidades
     */
    private Texture towerTexture;
    private Texture minionTexture;
    private Texture background;
    private Texture background2;
    private Texture rectan;

    /*
    limites de la pantalla, en pixeles
     */
    private final Vector2 boundaries = new Vector2(600, 400);
    /*
    mapa que relaciona cada entidad con su gerente gráfico
     */
    private HashMap<Entity, UIEntity> entities = new HashMap<Entity, UIEntity>();

    private OrthographicCamera camera;

    private Player player = new Player();

    private UIPlayer uiPlayer = new UIPlayer(player);

    /*
    retorna los boundaries
     */
    public Vector2 getBoundaries() {
        return this.boundaries;
    }
    /*
    crea las texturas e inicia los objetos para graficar
     */
    public UIManager() {
        background = new Texture("assets/background.png");
        background2 = new Texture("assets/black.png");
        towerTexture = new Texture("assets/red.png");
        minionTexture = new Texture("assets/basketball.png");
        rectan = new Texture("assets/rect.png");


        camera = new OrthographicCamera();
        camera.setToOrtho(false, boundaries.x, boundaries.y);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

    }

    /*
    método de dibujo
     */
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        camera.update();
        //batch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.draw(background, 0, 0, 500, Gdx.graphics.getHeight());
        batch.draw(background2, 500, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        for(UIEntity uiEntity: entities.values()) {
            uiEntity.draw(this);
        }

        uiPlayer.draw(this);
    }

    /*
    si un minion muere, lo saca de la pantalla
     */
    @Override
    public void minionKilled(Minion minion) {
        entities.remove(minion);
    }

    /*
    si se agrega una torre, la agrega
     */
    @Override
    public void towerAdded(Tower tower) {
        entities.put(tower, new UITowerEntity<Tower>(towerTexture, tower));
    }

    /*
    si se agrega un minion, lo agrega
     */
    @Override
    public void minionAdded(Minion minion) {
        entities.put(minion, new UIEntity<Minion>(minionTexture, minion));
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
    public void nada4(){}
}
