// model/Sampah.java
package model;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class Sampah sebagai blueprint sampah dengan atribut umum dan method abstrak.
 * Ini contoh abstract class yang dipakai sebagai superclass.
 */
public abstract class Sampah {
    protected Image img;
    protected String jenis;
    protected String fileName;
    protected int level;

    // Constructor overloading: level default 1
    public Sampah(String fileName) {
        this(fileName, 1);
    }

    public Sampah(String fileName, int level) {
        this.fileName = fileName;
        this.level = level;
        this.img = new ImageIcon(getClass().getResource("/assets/" + fileName)).getImage();
    }

    // Abstract method yang harus diimplementasikan subclass
    public abstract String getJenis();
    public abstract Sampah clone();

    // Getter dan Setter
    public Image getImage() { return img; }
    public String getFileName() { return fileName; }
    public int getLevel() { return level; }
    public void setImage(Image img) { this.img = img; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public void setLevel(int level) { this.level = level; }

    // Method overloading contoh: setLevel dengan opsi reset image
    public void setLevel(int level, boolean resetImage) {
        this.level = level;
        if (resetImage) {
            this.img = new ImageIcon(getClass().getResource("/assets/" + fileName)).getImage();
        }
    }
}
