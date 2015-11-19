package tower.defense.model;

import tower.defense.model.Minion.Minion;
import tower.defense.model.Tower.Tower;

/**
 * Created by Tomi on 08/11/2015.
 */

/*
interfaz que "escucha" lo que pasa en el juego
 */
public interface GameListener {

     void minionKilled(Minion minion);

     void towerAdded(Tower tower);

     void minionAdded(Minion minion);
}
