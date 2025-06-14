// model/SampahOrganik.java
package model;

import java.util.*;

/**
 * Subclass SampahOrganik yang mewarisi Sampah
 * Override method getJenis dan clone untuk tipe organik
 */
public class SampahOrganik extends Sampah {
    public SampahOrganik(String nama) {
        super(nama);
    }

    public SampahOrganik(String nama, int level) {
        super(nama, level);
    }

    @Override
    public String getJenis() {
        return "Organik";
    }

    @Override
    public Sampah clone() {
        return new SampahOrganik(this.fileName, this.level);
    }

    public static List<Sampah> getAll() {
        return Arrays.asList(
            new SampahOrganik("daun_kering.png"),
            new SampahOrganik("kulit_semangka.png"),
            new SampahOrganik("cangkang_telur.png"),
            new SampahOrganik("ranting_pohon.png"),
            new SampahOrganik("sisa_apel.png")
        );
    }
}
