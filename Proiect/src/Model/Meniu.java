package Model;

import java.util.List;

public class Meniu {
    private List<Produs> produse;

    public Meniu(List<Produs> produse) {
        this.produse = produse;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }
}

