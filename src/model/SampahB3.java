// model/SampahB3.java
package model;

import java.util.*;

/**
 * Subclass SampahB3 yang mewarisi Sampah
 */
public class SampahB3 extends Sampah {
    public SampahB3(String nama) {
        super(nama);
    }



    @Override
    public String getJenis() {
        return "B3";
    }

 @Override
public Sampah clone() {
    return new SampahB3(this.fileName);
}


    public static List<Sampah> getAll() {
        return Arrays.asList(
            new SampahB3("baterai.png"),
            new SampahB3("anti_serangga.png"),
            new SampahB3("lampu.png"),
            new SampahB3("obat.png")
        );
    }
}
