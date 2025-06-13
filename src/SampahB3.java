class SampahB3 extends Sampah {
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
}