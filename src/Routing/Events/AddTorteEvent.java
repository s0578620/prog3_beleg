package Routing.Events;

import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.EventObject;

public class AddTorteEvent extends EventObject {

    private String kuchentyp;
    private String hersteller;
    private Collection<Allergen> allergene;
    private int naehrwert;
    private int haltbarkeit;
    private BigDecimal preis;
    private String Obstsorte;
    private String Kremsorte;


    public AddTorteEvent(Object source, String Kuchentyp, String hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Collection<Allergen> Allergene, String Kremsorte, String Obstsorte){
        super(source);
        this.kuchentyp = Kuchentyp;
        this.hersteller = hersteller;
        this.allergene = Allergene;
        this.naehrwert = Naehrwert;
        this.haltbarkeit = Haltbarkeit;
        this.preis = Preis;
        this.Kremsorte = Kremsorte;
        this.Obstsorte = Obstsorte;
    }

    public String toString() {
        return "add Torte";
    }

    public String getKuchentyp() {
        return kuchentyp;
    }

    public String getHersteller() {
        return hersteller;
    }

    public Collection<Allergen> getAllergene() {
        return allergene;
    }

    public int getNaehrwert() {
        return naehrwert;
    }

    public int getHaltbarkeit() {
        return haltbarkeit;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public String getObstsorte() {
        return Obstsorte;
    }

    public String getKremsorte() {
        return Kremsorte;
    }
}
