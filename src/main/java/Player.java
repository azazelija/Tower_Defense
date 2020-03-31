import javax.swing.*;
import java.awt.*;

public class Player {
    private static Player player;

    private Image playerImage = new ImageIcon("src/images/player.png").getImage();

    private int playerX;
    private int playerY;

    private int health = 200;

    private boolean killed;
    private boolean buffed;

    private Player() { }

    public boolean isBuffed() {
        return buffed;
    }

    public void setBuffed(boolean buffed) {
        this.buffed = buffed;
    }

    public static Player getPlayer() {
        if (player == null)
            player = new Player();
        return player;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}
