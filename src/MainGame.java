import javax.swing.*;
import java.awt.*;

public class MainGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pilah Sampahmu!");
        JLabel labelStatus = new JLabel("", SwingConstants.CENTER);
        labelStatus.setFont(new Font("Arial", Font.BOLD, 20));
        GamePilahSampah panel = new GamePilahSampah(labelStatus);

        frame.setLayout(new BorderLayout());
        frame.add(labelStatus, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

