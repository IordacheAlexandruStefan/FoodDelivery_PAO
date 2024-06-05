package Main;

import Database.*;
import Model.*;
import Servicii.AuditService;
import Servicii.FileManager;

import java.util.List;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException {
        CrudService<Produs> produsService = new CrudService<>("produse", new ProdusMapper());
        CrudService<Client> clientService = new CrudService<>("clienti", new ClientMapper());
        CrudService<Adresa> adresaService = new CrudService<>("adrese", new AdresaMapper());
        CrudService<Meniu> meniuService = new CrudService<>("meniu", new MeniuMapper());

        Produs produs = new Produs("Pizza Margherita", 30);
        produsService.create(produs);

        List<Produs> produse = produsService.read();
        produse.forEach(p -> System.out.println(p.getNume() + " - " + p.getPret()));

        produs.setPret(35);
        produsService.update(produs, "1");

        Client client = new Client("Iordache Alexandru", "Str. Viilor", "0728616424");
        clientService.create(client);

        List<Client> clienti = clientService.read();
        clienti.forEach(p -> System.out.println(p.getNume() + " - " + p.getAdresa() + " - " + p.getTelefon()));

        client.setTelefon("0721193221");
        clientService.update(client, "1");

        Adresa adresa = new Adresa("Viilor", "Pitesti", "110111");
        adresaService.create(adresa);

        List<Adresa> adrese = adresaService.read();
        adrese.forEach(p -> System.out.println(p.getStrada() + " - " + p.getOras() + " - " + p.getCodPostal()));

        adresa.setCodPostal("075312");
        adresaService.update(adresa, "1");

        Meniu meniu = new Meniu(produse);
        meniuService.create(meniu);

//        List<Meniu> meniuri = meniuService.read();
//        meniuri.forEach(p -> System.out.println(p.getProduse()));
//
//        adresa.setCodPostal("075312");
//        adresaService.update(adresa, "1");

        AuditService auditService = AuditService.getInstance();
        FileManager<Produs> fileManagerProdus = FileManager.getInstance();
        List<Produs> produse2 = fileManagerProdus.readFromFile("produse.csv", Produs::parseCSV);
        //Produs pizza = new Produs("Pizza Margherita", 30);
        //Produs burger = new Produs("Cheeseburger", 25);
        Meniu meniu2 = new Meniu(produse2);

        Restaurant restaurant = new Restaurant("Pizza Plus", "Strada Viilor, Nr. 35", meniu2);
        Client client2 = new Client("Iordache Alexandru", "Strada Tineretului, Nr. 35", "0728616424");
        Adresa adresaLivrare = new Adresa("Strada Libertății, Nr. 20", "București", "010101");

        Comanda comanda1 = new Comanda(1, client, adresaLivrare);
        //comanda1.adaugaProdus(pizza);
        //comanda1.adaugaProdus(burger);
        comanda1.adaugaProdus(meniu2.getProduse().get(0));
        comanda1.adaugaProdus(meniu2.getProduse().get(1));
        auditService.logAction("Creare Comanda");

        comanda1.adaugaPlata("card");
        auditService.logAction("Efectuare Plata");

        System.out.println("Comanda ID: " + comanda1.getId() + " pentru clientul " + client2.getNume());
        System.out.println("Adresa de livrare: " + comanda1.getAdresaLivrare().getStrada());
        for (Produs produ : comanda1.getProduse()) {
            System.out.println("Produs: " + produ.getNume() + ", Pret: " + produ.getPret());
        }
        System.out.println("Total de plata: " + comanda1.calculeazaTotal() + " lei");
        System.out.println("Metodă de plata: " + comanda1.getPlata().getMetodaPlata());
    }
}


