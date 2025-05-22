package Game.Enemys;

import Game.Panels.GamePanel;


import java.util.ArrayList;


public class WaveManager {
    private static int waveCount;


    public WaveManager(GamePanel panel){
        WaveManager.waveCount= 0;
    }


    public static void sendWave(ArrayList<Enemy> enemies) {

            //LOL Maximus´s Schwanz sehr Groß JA JA !!!
            EnemyFormation.spawnEnemies(enemies,waveCount);
            waveCount++;




    }

    public static int getWaveCount() {
        return waveCount;
    }

    public static void setWaveCount(int waveCount) {
        WaveManager.waveCount = waveCount;
    }
}
