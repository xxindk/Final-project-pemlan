package model;

import javax.swing.*;
import java.awt.*;

public abstract class Sampah {
    protected Image img;
    protected String jenis;
    protected String fileName;

    public Sampah(String fileName) {
        this.fileName = fileName;
        this.img = new ImageIcon(getClass().getResource("/assets/" + fileName)).getImage();
    }

    public abstract String getJenis();

    public abstract Sampah clone();

    public Image getImage() {
        return img;
    }

    public String getFileName() {
        return fileName;
    }
}
