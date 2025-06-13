class SampahOrganik extends Sampah {
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
}