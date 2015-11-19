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
import tower.defense.model.Minion.MultipleMinion;
import tower.defense.model.Minion.RedMinion;
import tower.defense.model.Path;
import tower.defense.model.Player;
import tower.defense.model.Tower.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.geom.Area;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

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
    private Texture SimpleTowerTexture;
    private Texture AreaTowerTexture;
    private Texture BomberTowerTexture;
    private Texture FrezzeTowerTexture;
    private Texture TeslaTowerTexture;
    private Texture RedMinionTexture;
    private Texture MultipleMinionTexture;
    private Texture ElectricMinionTexture;
    private Texture background;
    private Texture background2;
    private Texture projectileTexture;

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

        background = new Texture("core/assets/anback.png");
        background2 = new Texture("core/assets/black.png");
        SimpleTowerTexture = new Texture("core/assets/SimpleTower.png");
        AreaTowerTexture = new Texture("core/assets/AreaTower.png");
        BomberTowerTexture = new Texture("core/assets/BomberTower.png");
        FrezzeTowerTexture = new Texture("core/assets/FrezzeTower.png");
        TeslaTowerTexture = new Texture("core/assets/ElectricTower.png");
        RedMinionTexture = new Texture("core/assets/RedMinion.png");
        MultipleMinionTexture = new Texture("core/assets/MultipleMinion.png");
        ElectricMinionTexture = new Texture("core/assets/ElectricMinion.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, boundaries.x, boundaries.y);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

    }

    /*
    método de dibujo
     */
    public void draw() {
        String msg;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        camera.update();
        //batch.setProjectionMatrix(camera.combined);
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);

        batch.begin();
        batch.draw(background, 0, 0, 500, Gdx.graphics.getHeight());
        batch.draw(SimpleTowerTexture,20,420, 50,50);
        bitmapFont.draw(getBatch(),"$150", 30, 415);
        batch.draw(AreaTowerTexture,120,420, 50,50);
        bitmapFont.draw(getBatch(),"$300", 130, 415);
        batch.draw(BomberTowerTexture,220,420, 50,50);
        bitmapFont.draw(getBatch(),"$600", 230, 415);
        batch.draw(FrezzeTowerTexture,320,420, 50,50);
        bitmapFont.draw(getBatch(),"$400", 330, 415);
        batch.draw(TeslaTowerTexture,420,420, 50,50);
        bitmapFont.draw(getBatch(),"$800", 430, 415);

        batch.draw(background2, 500, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//      for(int i=0; i<path.getRectangles().size()-1; i++){
//      for (Rectangle r : path.getRectangles())
//  getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
//          getShapeRenderer().rect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
//           getShapeRenderer().end();
//    }
        font.draw(batch, "Vidas: ", 525, 350);
        msg = "" + player.getLives();
        font.draw(batch, msg, 580, 350);
        batch.end();

        for(UIEntity uiEntity: entities.values()) {
            uiEntity.draw(this);
        }
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
        if(tower instanceof SimpleTower) {
            entities.put(tower, new UITowerEntity<Tower>(SimpleTowerTexture, tower));

        }else if(tower instanceof AreaTower){
            entities.put(tower, new UITowerEntity<Tower>(AreaTowerTexture, tower));

        }else if(tower instanceof BomberTower){
            entities.put(tower, new UITowerEntity<Tower>(BomberTowerTexture, tower));

        }else if(tower instanceof FreezeTower){
            entities.put(tower, new UITowerEntity<Tower>(FrezzeTowerTexture, tower));
        }else
            entities.put(tower, new UITowerEntity<Tower>(TeslaTowerTexture, tower));

    }

    /*
    si se agrega un minion, lo agrega
     */
    @Override
    public void minionAdded(Minion minion) {
        if (minion instanceof RedMinion ) {
            entities.put(minion, new UIEntity<Minion>(RedMinionTexture, minion));
        }else if(minion instanceof MultipleMinion){
            entities.put(minion, new UIEntity<Minion>(MultipleMinionTexture, minion));
        }else{
            entities.put(minion, new UIEntity<Minion>(ElectricMinionTexture, minion));
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Player GetPlayer(){return player;}

    public UITowerEntity getEntity(Tower t){
        return (UITowerEntity) entities.get(t);
    }

    public String stringMoney(int money){
        String a = "Money: "+money;
        return a;
    }

}

