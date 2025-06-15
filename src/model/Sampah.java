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

   public Sampah(String fileName) {
    this.fileName = fileName;
    this.img = new ImageIcon(getClass().getResource("/assets/" + fileName)).getImage();
}


    // Abstract method yang harus diimplementasikan subclass
    public abstract String getJenis();
    public abstract Sampah clone();

    // Getter dan Setter
    public Image getImage() { return img; }
    public String getFileName() { return fileName; }

    // Method overloading contoh: setLevel dengan opsi reset image
  
}
