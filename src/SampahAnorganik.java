class SampahAnorganik extends Sampah {
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
}