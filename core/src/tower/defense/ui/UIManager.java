package tower.defense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tower.defense.model.Entity;
import tower.defense.model.GameListener;
import tower.defense.model.Minion.Minion;
import tower.defense.model.Path;
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

    private Sound minionKilled = Gdx.audio.newSound(Gdx.files.internal("core/assets/facebook_ringtone_pop.mp3"));
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

    private Path path = Path.pathGetInstance();

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
        background = new Texture("core/assets/background.png");
        background2 = new Texture("core/assets/black.png");
        towerTexture = new Texture("core/assets/red.png");
        minionTexture = new Texture("core/assets/brazuca-02.png");
        //rectan = new Texture("assets/rect.png");


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

        for (Rectangle r : path.getRectangles()){
            getShapeRenderer().setColor(Color.BLUE);
            getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
            getShapeRenderer().rect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
            getShapeRenderer().end();
        }
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
        minionKilled.play();
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

}

