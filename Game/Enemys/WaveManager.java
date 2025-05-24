package Game.Enemys;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class WaveManager {
    private static int waveCount;
    static BufferedImage texture1;
    static BufferedImage texture2;

    public WaveManager(){
        WaveManager.waveCount= 0;
        {
            try {
                texture1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/Enemy.png")));
                texture2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/Enemy2.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void sendWave(ArrayList<Enemy> enemies) {

            //LOL Maximus´s Schwanz sehr Groß JA JA !!!
            EnemyFormation.spawnEnemies(enemies,waveCount, texture1, texture2);
            waveCount++;




    }

    public static int getWaveCount() {
        return waveCount;
    }

    public static void setWaveCount(int waveCount) {
        WaveManager.waveCount = waveCount;
    }
}
