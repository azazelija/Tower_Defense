import monsters.Monster;

import javax.swing.*;
import java.awt.*;

public class Tower {
    private Image towerImage = new ImageIcon("src/images/tower.png").getImage();

    private int towerX;
    private int towerY;

    private final int damage = 5;

    private boolean right;
    private boolean left;

    private int radius;
    private Monster target;

    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Tower(int towerX, int towerY, int radius) {
        this.towerX = towerX;
        this.towerY = towerY;
        this.radius = radius;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public Image getTowerImage() {
        return towerImage;
    }

    public void setTowerImage(Image appleImage) {
        this.towerImage = appleImage;
    }

    public int getTowerX() {
        return towerX;
    }

    public void setTowerX(int towerX) {
        this.towerX = towerX;
    }

    public int getTowerY() {
        return towerY;
    }

    public void setTowerY(int towerY) {
        this.towerY = towerY;
    }

    public int getDamage() {
        return damage;
    }



    //-----------------------------
    public int getRadius() {
        return radius;
    }

    public Monster getTarget() {
        return target;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }
    //-----------------------------
}
