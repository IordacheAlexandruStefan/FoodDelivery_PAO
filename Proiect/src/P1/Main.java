package P1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuditService auditService = AuditService.getInstance();
        FileManager<Produs> fileManagerProdus = FileManager.getInstance();
        List<Produs> produse = fileManagerProdus.readFromFile("produse.csv", Produs::parseCSV);
        //Produs pizza = new Produs("Pizza Margherita", 30);
        //Produs burger = new Produs("Cheeseburger", 25);
        Meniu meniu = new Meniu(produse);

        Restaurant restaurant = new Restaurant("Pizza Plus", "Strada Viilor, Nr. 35", meniu);
        Client client = new Client("Iordache Alexandru", "Strada Tineretului, Nr. 35", "0728616424");
        Adresa adresaLivrare = new Adresa("Strada Libertății, Nr. 20", "București", "010101");

        Comanda comanda1 = new Comanda(1, client, adresaLivrare);
        //comanda1.adaugaProdus(pizza);
        //comanda1.adaugaProdus(burger);
        comanda1.adaugaProdus(meniu.getProduse().get(0));
        comanda1.adaugaProdus(meniu.getProduse().get(1));
        auditService.logAction("Creare Comanda");

        comanda1.adaugaPlata("card");
        auditService.logAction("Efectuare Plata");

        // Afisare detalii comandă
        System.out.println("Comanda ID: " + comanda1.getId() + " pentru clientul " + client.getNume());
        System.out.println("Adresa de livrare: " + comanda1.getAdresaLivrare().getStrada());
        for (Produs produs : comanda1.getProduse()) {
            System.out.println("Produs: " + produs.getNume() + ", Pret: " + produs.getPret());
        }
        System.out.println("Total de plată: " + comanda1.calculeazaTotal() + " lei");
        System.out.println("Metodă de plată: " + comanda1.getPlata().getMetodaPlata());
    }
}


