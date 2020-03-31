import monsters.Maik_Vazovski;
import monsters.Monster;
import monsters.Sallivan;
import weapons.Apple;
import weapons.Pineapple;
import weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel implements ActionListener {

    private int gold;

    private Player player;
    private Weapon apple;
    private Monster[] monster;
    private Tower[] towers;
    private Weapon[] pineapples;

    private int sec;

    private boolean moveLeft;
    private boolean moveRight;

    private boolean addTower;
    private boolean deleteTower;

    private boolean inGame;
    private boolean win;

    public Game() {
        setBackground(Color.BLACK);
        init();
        addKeyListener(new KeyListener());
        setFocusable(true);
    }

    private void init() {
        gold = 0;

        player = Player.getPlayer();
        player.setPlayerX(450);
        player.setPlayerY(680);

        init_monster();
        init_tower();
        init_pineapple();

        Timer timer = new Timer(10, this);
        timer.start();

        Timer clock;
        (clock = new Timer(1000, this)).addActionListener(e -> sec++);
        clock.start();

        inGame = true;
    }

    private void init_monster() {
        monster = new Monster[100];
        int y = -26500;
        int i = -1;
        while (++i < monster.length) {
            Random r = new Random();
            int low = 200;
            int high = 800;
            int result = r.nextInt(high - low) + low;
            if (i % 2 == 0) {
                monster[i] = new Maik_Vazovski(result, y, 50);
                monster[i].setMonsterImage(new ImageIcon("src/images/monster2.png").getImage());
                monster[i].setDamage(20);
            } else {
                monster[i] = new Sallivan(result, y, 70);
                monster[i].setMonsterImage(new ImageIcon("src/images/monster.png").getImage());
                monster[i].setDamage(30);
            }
            if (i <= 20) {
                y += 150;
            } else if (i <= 40) {
                y += 200;
            } else if (i <= 80) {
                y += 300;
            } else if (i <= 100) {
                y += 400;
            }
        }
    }

    private void init_tower() {
        towers = new Tower[2];
        towers[0] = new Tower(0, 50, 500);
        towers[1] = new Tower(0, 400, 1000);
    }

    private void init_pineapple() {
        apple = new Apple(455, 640);
        apple.setWeaponImage(new ImageIcon("src/images/apple.png").getImage());
        apple.setDamage(10);

        pineapples = new Pineapple[2];
        Pineapple pineapple0 = new Pineapple(156, 150);
        Pineapple pineapple1 = new Pineapple(156, 500);

        pineapple0.setDamage(5);
        pineapple1.setDamage(5);

        pineapple0.setWeaponImage(new ImageIcon("src/images/pineapple_left.png").getImage());
        pineapple1.setWeaponImage(new ImageIcon("src/images/pineapple_left.png").getImage());

        pineapples[0] = pineapple0;
        pineapples[1] = pineapple1;

    }

    private void draw_hp(Graphics g) {
        String hp = "Player HP: " + String.valueOf(player.getHealth());
        Font f = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.red);
        g.setFont(f);
        g.drawString(hp, 10, 30);
    }

    private void draw_time(Graphics g) {
        String timeStr = "Time: " + String.format("%02d:%02d", (sec % 3600) / 60, sec % 60);
        Font f2 = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.red);
        g.setFont(f2);
        g.drawString(timeStr, 850, 30);
    }

    private void draw_gold(Graphics g) {
        String gld = "Gold: " + String.valueOf(gold) + "\nTower costs 250";
        Font f3 = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.yellow);
        g.setFont(f3);
        g.drawString(gld, 10, 50);
    }

    private void draw_win(Graphics g) {
        String s = "YOU ARE WIN";
        g.setColor(Color.YELLOW);
        Font f = new Font("Arial", Font.BOLD, 100);
        g.setFont(f);
        g.drawString(s, 200, 200);
    }

    private void draw_game_over(Graphics g) {
        String s = "GAME OVER";
        g.setColor(Color.white);
        Font f = new Font("Arial", Font.BOLD, 100);
        g.setFont(f);
        g.drawString(s, 200, 200);
    }

    private void draw_obj(Graphics g) {
        int i = -1;
        while (++i < monster.length) {
            g.drawImage(monster[i].getMonsterImage(), monster[i].getMonsterX(), monster[i].getMonsterY(), this);
        }
        g.drawImage(player.getPlayerImage(), player.getPlayerX(), player.getPlayerY(), this);
        g.drawImage(apple.getWeaponImage(), apple.getX(), apple.getY(), this);
        i = -1;
        while (++i < towers.length) {
            if (towers[i].isVisible())
                g.drawImage(towers[i].getTowerImage(), towers[i].getTowerX(), towers[i].getTowerY(), this);
        }
    }

    private void drawTowersFire(Graphics g) {
        for (int j = 0; j < towers.length; j++) {
            if (towers[j].getTarget() != null & towers[j].isVisible()) {
                g.drawImage(pineapples[j].getWeaponImage(), pineapples[j].getX(), pineapples[j].getY(), this);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            draw_obj(g);
            draw_hp(g);
            draw_time(g);
            draw_gold(g);
            drawTowersFire(g);
        }
        if (!inGame) {
            if (win) {
                draw_win(g);
            } else {
                draw_game_over(g);
            }
        }
    }

    private void checkForStopping() {
        int i = -1;
        if (apple.getY() == 0) {
            apple.setY(680);
            apple.setWeaponImage(new ImageIcon("src/images/apple.png").getImage());
            apple.setBuffed(false);
            while (++i < monster.length) {
                monster[i].setBuffed(false);
            }
        }

        i = -1;
        while (++i < monster.length) {
            if (monster[i].getMonsterY() == 800) {
                if (player.isBuffed()) {
                    player.setBuffed(false);
                }
            }
        }

        if (monster[0].getMonsterY() >= 800 || monster[0].isKilled()) {
            win = true;
            inGame = false;
        }
    }

    private void checkForKilledMonster() {
        int i = -1;
        while (++i < monster.length) {
            if ((Math.abs(monster[i].getMonsterY() - apple.getY()) <= 50) && (Math.abs(monster[i].getMonsterX() - apple.getX()) <= 50) && !monster[i].isKilled()) {
                if (!monster[i].isBuffed() && !apple.isBuffed()) {
                    monster[i].setHealth(monster[i].getHealth() - apple.getDamage());
                    monster[i].setBuffed(true);
                }
                apple.setWeaponImage(new ImageIcon("").getImage());
                apple.setBuffed(true);
            }

            if (monster[i].getHealth() <= 0) {
                monster[i].setMonsterImage(new ImageIcon("src/images/killed_monster.png").getImage());
                if (!monster[i].isKilled()) {
                    gold += 100;
                }
                monster[i].setKilled(true);
            }
        }
    }

    private void checkForKilledPlayer() {
        int i = -1;
        while (++i < monster.length) {
            if ((Math.abs(monster[i].getMonsterY() - player.getPlayerY()) <= 50) && (Math.abs(monster[i].getMonsterX() - player.getPlayerX()) <= 50) && !monster[i].isKilled()) {
                if (!player.isBuffed()) {
                    player.setHealth(player.getHealth() - monster[i].getDamage());
                    player.setBuffed(true);
                }
            }
            if (player.getHealth() <= 0) {
                inGame = false;
            }
        }
    }


    private void move() {
        if (inGame) {
            if (moveLeft) {
                player.setPlayerX(player.getPlayerX() - 10);
                moveLeft = false;
            }
            if (moveRight) {
                player.setPlayerX(player.getPlayerX() + 10);
                moveRight = false;
            }
            checkForStopping();
            checkForKilledMonster();
            checkForKilledPlayer();
            int i = -1;
            while (++i < monster.length) {
                monster[i].setMonsterY(monster[i].getMonsterY() + 1);
            }
            apple.setY(apple.getY() - 10);
            apple.setX(player.getPlayerX() + 5);

            //************************************************************************
            findTowersTarget();
            for (int j = 0; j < towers.length; j++) {
                towerFire(j);
            }
            //************************************************************************
        }
    }

    private void add_delete_tower() {
        int i = -1;
        if (addTower) {
            if (gold >= 250) {
                while (++i < towers.length) {
                    if (!towers[i].isVisible()) {
                        towers[i].setVisible(true);
                        addTower = false;
                        gold -= 250;
                        break;
                    }
                }
            }
        } else if (deleteTower) {
            while (++i < towers.length) {
                if (towers[i].isVisible()) {
                    towers[i].setVisible(false);
                    deleteTower = false;
                    gold += 250;
                    break;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
            add_delete_tower();
        }
        repaint();
    }

    private class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && player.getPlayerX() > 0) {
                moveLeft = true;
                moveRight = false;
            }
            if (key == KeyEvent.VK_RIGHT && player.getPlayerX() < 940) {
                moveRight = true;
                moveLeft = false;
            }
            if (key == KeyEvent.VK_D) {
                addTower = false;
                deleteTower = true;
            }
            if (key == KeyEvent.VK_A) {
                addTower = true;
                deleteTower = false;
            }
        }

    }

    //-------------------------------------------------------------------
    private void towerFire(int towerNumber) {
        if (towers[towerNumber].getTarget() != null & towers[towerNumber].isVisible()) {
            pineapples[towerNumber].setX(pineapples[towerNumber].getX() + 25);

            if (pineapples[towerNumber].getX() >= towers[towerNumber].getRadius()) {
                pineapples[towerNumber].setX(0);
                pineapples[towerNumber].setBuffed(false);
            }
            if (pineapples[towerNumber].getX() + 80 >= towers[towerNumber].getTarget().getMonsterX() - 40) {
                if (!pineapples[towerNumber].isBuffed()) {
                    pineapples[towerNumber].setBuffed(true);
                    towers[towerNumber].getTarget().setHealth(towers[towerNumber].getTarget().getHealth() - pineapples[towerNumber].getDamage());
                }
            }
        }
    }

    private Monster getTarget(int towerNumber, Monster[] monster) {
        for (int j = 99; j > 0; j--) {
            if (monster[j].getMonsterY() + 50 >= towers[towerNumber].getTowerY() & monster[j].getMonsterY() < towers[towerNumber].getTowerY() + 100) {
                if (monster[j].getMonsterX() < towers[towerNumber].getRadius() & !monster[j].isKilled()) {
                    return monster[j];
                }
            }
        }
        return null;
    }

    private void findTowersTarget() {
        for (int j = 0; j < towers.length; j++) {
            towers[j].setTarget(getTarget(j, monster));
        }
    }

    //--------------------------------------------------------------------
}