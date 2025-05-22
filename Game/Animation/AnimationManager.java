package Game.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AnimationManager {

    public BufferedImage[] textures;
    BufferedImage idleTexture;
    int width, height, delay;
    long lastFrameTime;
    int currentFrame;
    int frameStart, frameEnd;
    boolean isPlaying = false;
    boolean loop = false;
    String idleTexturePath;

    public AnimationManager(int width, int height, int frameCount, int delay, String[] texturesPaths, String idleTexturePath) {
        this.width = width;
        this.height = height;
        this.delay = delay;
        this.textures = new BufferedImage[frameCount];
        this.idleTexturePath = idleTexturePath;

        try {
            idleTexture = ImageIO.read(Objects.requireNonNull(getClass().getResource(idleTexturePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < frameCount; i++) {
            try {
                textures[i] = ImageIO.read(Objects.requireNonNull(getClass().getResource(texturesPaths[i])));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startAnimation(int start, int end, boolean loop) {
        this.frameStart = start;
        this.frameEnd = end;
        this.currentFrame = start;
        this.lastFrameTime = System.currentTimeMillis();
        this.isPlaying = true;
        this.loop = loop;
    }

    public void update() {
        if (!isPlaying) return;

        long now = System.currentTimeMillis();
        if (now - lastFrameTime >= delay) {
            currentFrame++;
            if (currentFrame >= frameEnd) {
                if (loop) {
                    currentFrame = frameStart;
                } else {
                    isPlaying = false; // beendet Animation
                }
            }
            lastFrameTime = now;
        }
    }

    public void draw(Graphics g, int x, int y) {
        if (isPlaying && currentFrame < textures.length) {
            g.drawImage(textures[currentFrame], x, y, width, height, null);
        } else if (idleTexture != null) {
            g.drawImage(idleTexture, x, y, width, height, null);
        }
    }


    public boolean isPlaying() {
        return isPlaying;
    }

    public int getHeight() {return this.height;}
}


