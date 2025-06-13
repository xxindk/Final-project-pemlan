import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;


class GamePilahSampah extends JPanel implements ActionListener, KeyListener {
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

    public GamePilahSampah(JLabel status) {
        this.status = status;
        setFocusable(true);
        addKeyListener(this);
        loadAssets();
        initSampah();
        restart();
    }

    private void loadAssets() {
        try {
            background = ImageIO.read(getClass().getResource("/assets/background.png"));
            tempatOrg = new ImageIcon(getClass().getResource("/assets/tempat_organik.png")).getImage();
            tempatAn = new ImageIcon(getClass().getResource("/assets/tempat_anorganik.png")).getImage();
            tempatB3 = new ImageIcon(getClass().getResource("/assets/tempat_b3.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSampah() {
        semuaSampah.add(new SampahOrganik("daun_kering.png"));
        semuaSampah.add(new SampahOrganik("kulit_semangka.png"));
        semuaSampah.add(new SampahOrganik("cangkang_telur.png"));
        semuaSampah.add(new SampahOrganik("ranting_pohon.png"));
        semuaSampah.add(new SampahOrganik("sisa_apel.png"));
        semuaSampah.add(new SampahOrganik("sisa_sayuran.png"));
        semuaSampah.add(new SampahOrganik("tulang_ikan.png"));
        semuaSampah.add(new SampahAnorganik("kantong_plastik.png"));
        semuaSampah.add(new SampahAnorganik("botol_plastik.png"));
        semuaSampah.add(new SampahAnorganik("bungkus_ciki.png"));
        semuaSampah.add(new SampahAnorganik("kaleng_minuman.png"));
        semuaSampah.add(new SampahAnorganik("kardus_bekas.png"));
        semuaSampah.add(new SampahAnorganik("mika.png"));
        semuaSampah.add(new SampahAnorganik("sampah_kertas.png"));
        semuaSampah.add(new SampahB3("baterai.png"));
       
    }

    private void restart() {
        waktu = 0;
        gameOver = false;
        poin = 0;
        animasi = false;
        nextSampah();
        timer = new javax.swing.Timer(1000 / 60, this); // 60 FPS
        timer.start();
        status.setText("Gunakan tombol panah kanan, bawah, kiri untuk memilah sampah");
        repaint();
    }

    private void nextSampah() {
        Random rand = new Random();
        aktif = semuaSampah.get(rand.nextInt(semuaSampah.size())).clone(); // Buat objek baru
        waktu = 0;
        animasi = false;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && aktif != null) {
            waktu++;
            if (!animasi && waktu / 60 >= 7) { // 20 detik
                gameOver = true;
                timer.stop();
                status.setText("Tekan ENTER untuk mulai");
            }
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Gambar background gelap
        if (background != null) {
            g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            g2.setColor(new Color(0, 0, 0, 150)); // gelapkan
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        drawBins(g2);

        if (aktif != null && aktif.img != null) {
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

            
          int originalWidth = aktif.img.getWidth(this);
int originalHeight = aktif.img.getHeight(this);
int newWidth = (int)(originalWidth * 0.1);
int newHeight = (int)(originalHeight * 0.1);

g2.drawImage(aktif.img, x, y, newWidth, newHeight, this);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 24));
g2.setColor(Color.WHITE);
g2.drawString("Skor: " + poin, 65, 40);


        if (gameOver) {
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setFont(new Font("Arial", Font.BOLD, 64));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", getWidth() / 2 - 200, getHeight() / 2);
            g2.setFont(new Font("Arial", Font.BOLD, 32));

g2.setColor(Color.BLACK);
g2.drawString("Total Skor: " + poin, getWidth() / 2 - 120, getHeight() / 2 + 50);

        }
    }

    private void drawBins(Graphics2D g2) {
        int y = getHeight() - 200;
        if (tempatOrg != null) g2.drawImage(tempatOrg, 50, y, 300, 160, this);
        if (tempatAn != null) g2.drawImage(tempatAn, 250, y, 300, 160, this);
        if (tempatB3 != null) g2.drawImage(tempatB3, 450, y, 300, 160, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
            restart();
            return;
        }
        if (aktif == null || animasi || gameOver) return;

        String input = null;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) input = "Organik";
        else if (key == KeyEvent.VK_DOWN) input = "Anorganik";
        else if (key == KeyEvent.VK_RIGHT) input = "B3";

        if (input == null) return;

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

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
