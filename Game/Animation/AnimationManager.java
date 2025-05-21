package Game.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AnimationManager {

    BufferedImage[] textures;
    int width, height, delay, frameCount;
    long oldTime;
    public AnimationManager(int width, int height, int frameCount, String[] texturesPaths, int delay) {
        this.width = width;
        this.height = height;
        this.delay = delay;
        this.frameCount = frameCount;
        this.textures = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            try {
                textures[i] = ImageIO.read(Objects.requireNonNull(getClass().getResource(texturesPaths[i])));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void animate(int x, int y, int frameStart, Graphics g) {
        for (int i = frameStart; i < frameCount;) {
            if(System.currentTimeMillis() - oldTime >= delay) {
                g.drawImage(textures[i], x, y, width, height, null);
                i++;
            }
        }
    }


}
