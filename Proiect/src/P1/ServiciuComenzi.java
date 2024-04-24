package P1;

import java.util.ArrayList;
import java.util.List;

public class ServiciuComenzi {
    private List<Comanda> comenzi;

    public ServiciuComenzi() {
        this.comenzi = new ArrayList<>();
    }

    public void adaugaComanda(Comanda comanda) {
        comenzi.add(comanda);
    }

    public void stergeComanda(Comanda comanda) {
        comenzi.remove(comanda);
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public Comanda cautaComandaDupaId(int id) {
        for (Comanda comanda : comenzi) {
            if (comanda.getId() == id) {
                return comanda;
            }
        }
        return null;
    }
}

