package monsters;

import java.awt.*;

public abstract class Monster {
    private Image monsterImage;

    private int monsterX;
    private int monsterY;

    private int health;
    private int damage;

    private boolean buffed;
    private boolean killed;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isBuffed() {
        return buffed;
    }

    public void setBuffed(boolean buffed) {
        this.buffed = buffed;
    }

    public Monster(int monsterX, int monsterY, int health) {
        this.monsterX = monsterX;
        this.monsterY = monsterY;
        this.health = health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setMonsterImage(Image monsterImage) {
        this.monsterImage = monsterImage;
    }

    public void setMonsterX(int monsterX) {
        this.monsterX = monsterX;
    }

    public void setMonsterY(int monsterY) {
        this.monsterY = monsterY;
    }

    public Image getMonsterImage() {
        return monsterImage;
    }

    public int getMonsterX() {
        return monsterX;
    }

    public int getMonsterY() {
        return monsterY;
    }
}
