package model;

import java.util.*;

public class SampahAnorganik extends Sampah {
    public SampahAnorganik(String nama) {
        super(nama);
    }

    @Override
    public String getJenis() {
        return "Anorganik";
    }

    @Override
    public Sampah clone() {
        return new SampahAnorganik(this.fileName);
    }

    public static List<Sampah> getAll() {
        return Arrays.asList(
                new SampahAnorganik("botol_plastik.png"),
                new SampahAnorganik("kaleng_minuman.png"),
                new SampahAnorganik("kardus_bekas.png"),
                new SampahAnorganik("mika.png"),
                new SampahAnorganik("sampah_kertas.png"));
    }
}
