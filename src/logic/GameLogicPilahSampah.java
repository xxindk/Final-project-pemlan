package logic;

import model.*;
import java.util.*;

/**
 * Class utama untuk mengatur logika game Pilah Sampah
 */
public class GameLogicPilahSampah {
    private List<Sampah> semuaSampah;
    private Random rand;
    private int poin;
    private boolean gameOver;
    private Sampah aktif;

    public GameLogicPilahSampah() {
        semuaSampah = new ArrayList<>();
        semuaSampah.addAll(SampahOrganik.getAll());
        semuaSampah.addAll(SampahAnorganik.getAll());
        semuaSampah.addAll(SampahB3.getAll());
        rand = new Random();
        resetGame();
    }

    // Reset game, dengan method overloading
    public void resetGame() {
        resetGame(0);
    }

    public void resetGame(int startPoints) {
        poin = startPoints;
        gameOver = false;
        nextSampah();
    }

    public Sampah getSampahAktif() {
        return aktif;
    }

    public int getPoin() {
        return poin;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Buat sampah baru secara acak untuk ditampilkan
     */
    public void nextSampah() {
        aktif = semuaSampah.get(rand.nextInt(semuaSampah.size())).clone();
    }

    /**
     * Cek jawaban user, return true jika benar, false jika salah (game over)
     */
    public boolean checkJawaban(String input) {
        if (gameOver || aktif == null) return false;

        if (aktif.getJenis().equals(input)) {
            poin++;
            nextSampah();
            return true;
        } else {
            gameOver = true;
            return false;
        }
    }
}
