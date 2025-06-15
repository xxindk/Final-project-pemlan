package model;

import java.util.*;

public class SampahOrganik extends Sampah {
    public SampahOrganik(String nama) {
        super(nama);
    }

    @Override
    public String getJenis() {
        return "Organik";
    }

    @Override
    public Sampah clone() {
        return new SampahOrganik(this.fileName);
    }

    public static List<Sampah> getAll() {
        return Arrays.asList(
                new SampahOrganik("daun_kering.png"),
                new SampahOrganik("kulit_semangka.png"),
                new SampahOrganik("cangkang_telur.png"),
                new SampahOrganik("ranting_pohon.png"),
                new SampahOrganik("sisa_apel.png"));
    }
}
