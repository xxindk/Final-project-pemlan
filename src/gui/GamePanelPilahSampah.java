
package gui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class GamePanelPilahSampah extends JPanel implements ActionListener {
    private java.util.List<Sampah> semuaSampah = new ArrayList<>();
    private Sampah aktif;
    private BufferedImage background;
    private Image tempatOrg, tempatAn, tempatB3;
    private javax.swing.Timer timer;
    private int waktu = 0;
    private boolean gameOver = false;
    private boolean animasi = false;
    private int animX, animY, targetX, targetY;
    private JLabel status;
    private int poin;
    private String namaPemain = "Pengguna";

    public void setNamaPemain(String nama) {
        this.namaPemain = nama;
    }

    public String getNamaPemain() {
        return namaPemain;
    }

    public GamePanelPilahSampah(JLabel status) {
        this.status = status;
        setFocusable(true);
        loadAssets();
        initSampah();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    restart();
                    return;
                }
                if (aktif == null || animasi || gameOver)
                    return;

                String input = null;
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT)
                    input = "Organik";
                else if (key == KeyEvent.VK_DOWN)
                    input = "Anorganik";
                else if (key == KeyEvent.VK_RIGHT)
                    input = "B3";

                if (input == null)
                    return;

                if (!aktif.getJenis().equals(input)) {
                    gameOver = true;
                    timer.stop();
                    status.setText("Game Over! Tekan ENTER untuk restart");
                    repaint();
                } else {
                    poin++;
                    animasi = true;
                    animX = getWidth() / 2 - 60;
                    animY = 200;
                    if (input.equals("Organik")) {
                        targetX = 100;
                        targetY = getHeight() - 170;
                    } else if (input.equals("Anorganik")) {
                        targetX = 320;
                        targetY = getHeight() - 170;
                    } else {
                        targetX = 540;
                        targetY = getHeight() - 170;
                    }
                }
            }
        });

        this.namaPemain = JOptionPane.showInputDialog(this, "Masukkan nama kamu:");
        if (this.namaPemain == null || this.namaPemain.trim().isEmpty()) {
            this.namaPemain = "Pemain";
        }
        restart();
    }

    private void loadAssets() {
        try {
            background = javax.imageio.ImageIO.read(getClass().getResource("/assets/background.png"));
            tempatOrg = new ImageIcon(getClass().getResource("/assets/tempat_organik.png")).getImage();
            tempatAn = new ImageIcon(getClass().getResource("/assets/tempat_anorganik.png")).getImage();
            tempatB3 = new ImageIcon(getClass().getResource("/assets/tempat_b3.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSampah() {
        semuaSampah.addAll(SampahOrganik.getAll());
        semuaSampah.addAll(SampahAnorganik.getAll());
        semuaSampah.addAll(SampahB3.getAll());
    }

    private void restart() {
        waktu = 0;
        gameOver = false;
        poin = 0;
        animasi = false;
        nextSampah();
        timer = new javax.swing.Timer(1000 / 60, this);
        timer.start();
        status.setText("Gunakan tombol panah kanan, bawah, kiri untuk memilah sampah");
        repaint();
    }

    private void nextSampah() {
        Random rand = new Random();
        aktif = semuaSampah.get(rand.nextInt(semuaSampah.size())).clone();
        waktu = 0;
        animasi = false;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && aktif != null) {
            waktu++;
            if (!animasi && waktu / 60 >= 7) {
                gameOver = true;
                timer.stop();
                status.setText("Tekan ENTER untuk mulai kembali");
            }
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (background != null) {
            g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        drawBins(g2);
        if (aktif != null && aktif.getImage() != null) {
            int x = getWidth() / 2 - 90;
            int y = 170;
            if (waktu / 60 >= 4) {
                x += (int) (Math.random() * 10 - 5);
                y += (int) (Math.random() * 10 - 5);
            }
            if (animasi) {
                x = animX;
                y = animY;
                animX += (targetX - animX) / 2;
                animY += (targetY - animY) / 2;
                if (Math.abs(targetX - animX) < 5 && Math.abs(targetY - animY) < 5) {
                    animasi = false;
                    nextSampah();
                    return;
                }
            }
            int newWidth = (int) (aktif.getImage().getWidth(this) * 0.1);
            int newHeight = (int) (aktif.getImage().getHeight(this) * 0.1);
            g2.drawImage(aktif.getImage(), x, y, newWidth, newHeight, this);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.WHITE);
        g2.drawString("Halo, " + namaPemain + "! Ayo mulai pisahkan sampahmu!", 65, 30);
        g2.drawString("Poin kamu sekarang " + poin, 65, 60);
        if (gameOver) {
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setFont(new Font("Arial", Font.BOLD, 64));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", getWidth() / 2 - 200, getHeight() / 2);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            g2.setColor(Color.BLACK);
            g2.drawString("Total poin " + namaPemain + ": " + poin, getWidth() / 2 - 120, getHeight() / 2 + 50);
        }
    }

    private void drawBins(Graphics2D g2) {
        int y = getHeight() - 200;
        if (tempatOrg != null)
            g2.drawImage(tempatOrg, 50, y, 300, 160, this);
        if (tempatAn != null)
            g2.drawImage(tempatAn, 250, y, 300, 160, this);
        if (tempatB3 != null)
            g2.drawImage(tempatB3, 450, y, 300, 160, this);
    }
}
