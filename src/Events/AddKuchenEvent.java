package Events;

import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.EventObject;

public class AddKuchenEvent extends EventObject {

    private String kuchentyp;
    private String hersteller;
    private Collection<Allergen> allergene;
    private int naehrwert;
    private int haltbarkeit;
    private BigDecimal preis;
    private String topping;

    public AddKuchenEvent(Object source, String Kuchentyp, String hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Collection<Allergen> Allergene, String Topping){
        super(source);
        this.kuchentyp = Kuchentyp;
        this.hersteller = hersteller;
        this.allergene = Allergene;
        this.naehrwert = Naehrwert;
        this.haltbarkeit = Haltbarkeit;
        this.preis = Preis;
        this.topping = Topping;
    }


    public String toString() {
        return "add Kuchen";
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


    public String getTopping() {
        return topping;
    }
}
