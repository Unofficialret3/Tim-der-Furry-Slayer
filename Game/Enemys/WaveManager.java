package Game.Enemys;

import Game.Panels.GamePanel;


import java.util.ArrayList;


public class WaveManager {
    private static int waveCount;


    public WaveManager(GamePanel panel){
        waveCount= 0;


    }


    public static void sendWave(ArrayList<Enemy> enemies) {

            //TODO: besonders einfache oder so
            EnemyFormation.spawnEnemies(enemies);
            waveCount++;




    }
}
