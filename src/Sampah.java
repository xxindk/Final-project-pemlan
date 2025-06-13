import javax.swing.*;
import java.awt.*;

abstract class Sampah {
    protected Image img;
    protected String jenis;
    protected String fileName; // Tambahan untuk clone

    public Sampah(String fileName) {
        this.fileName = fileName;
        this.img = new ImageIcon(getClass().getResource("/assets/" + fileName)).getImage();
    }

    public abstract String getJenis();
    public abstract Sampah clone(); // Tambahkan metode clone
}