package P1;

public class Restaurant {
    private String nume;
    private String adresa;
    private Meniu meniu;

    public Restaurant(String nume, String adresa, Meniu meniu) {
        this.nume = nume;
        this.adresa = adresa;
        this.meniu = meniu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Meniu getMeniu() {
        return meniu;
    }

    public void setMeniu(Meniu meniu) {
        this.meniu = meniu;
    }
}

