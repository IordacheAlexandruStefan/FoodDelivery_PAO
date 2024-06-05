package Model;

public class Plata {
    private double suma;
    private String metodaPlata;  // ex. cash, card, transfer bancar

    public Plata(double suma, String metodaPlata) {
        this.suma = suma;
        this.metodaPlata = metodaPlata;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }
}

