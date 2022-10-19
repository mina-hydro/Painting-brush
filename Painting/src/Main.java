import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1900, 900);
        frame.setContentPane(new Panel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}