import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Tower_Dependency");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocation(400,400);
        add(new Game());
        setVisible(true);
    }
    public static void main(String[] args) {
        Main main = new Main();
    }
}
