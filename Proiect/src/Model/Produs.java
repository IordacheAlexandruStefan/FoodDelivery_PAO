package Model;

public class Produs {
    private String nume;
    private double pret;

    public Produs(String nume, double pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public static Produs parseCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Produs(parts[0], Double.parseDouble(parts[1]));
    }

    public String toCSV() {
        return nume + "," + pret;
    }
}
