package weapons;

import java.awt.*;

public abstract class Weapon {
    private Image weaponImage;

    private int x;
    private int y;
    private boolean buffed;
    private int damage;

    public Weapon(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getWeaponImage() {
        return weaponImage;
    }

    public void setWeaponImage(Image weaponImage) {
        this.weaponImage = weaponImage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isBuffed() {
        return buffed;
    }

    public void setBuffed(boolean buffed) {
        this.buffed = buffed;
    }
}
