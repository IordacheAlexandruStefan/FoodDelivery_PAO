package P1;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class Comanda {
    private int id;
    private Client client;
    private List<Produs> produse;
    private Adresa adresaLivrare;

    public Plata getPlata() {
        return plata;
    }

    public void setPlata(Plata plata) {
        this.plata = plata;
    }

    private Plata plata;

    public Comanda(int id, Client client, Adresa adresaLivrare) {
        this.id = id;
        this.client = client;
        this.produse = new ArrayList<>();
        this.adresaLivrare = adresaLivrare;
        this.plata = null;  // Plata se adaugă după confirmarea comenzii
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }

    public void stergeProdus(Produs produs) {
        produse.remove(produs);
    }

    public double calculeazaTotal() {
        double total = 0;
        for (Produs produs : produse) {
            total += produs.getPret();
        }
        return total;
    }

    public void adaugaPlata(String metodaPlata) {
        this.plata = new Plata(calculeazaTotal(), metodaPlata);
    }

    public int getId() {
        return id;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public Adresa getAdresaLivrare() {
        return adresaLivrare;
    }

    public void setAdresaLivrare(Adresa adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }
}

