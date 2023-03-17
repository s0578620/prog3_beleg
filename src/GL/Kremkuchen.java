package GL;

import vertrag.Allergen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Kremkuchen extends Obj implements vertrag.Kremkuchen,Serializable {

    private String Kremsorte;

    public Kremkuchen(String Kuchentyp,vertrag.Hersteller Hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Date Inspektionsdatum, Collection<Allergen> Allergens, String Kremsorte, Date insertDate) {
        super(Kuchentyp,Hersteller, Preis, Naehrwert, Haltbarkeit, Inspektionsdatum, Allergens, insertDate);
        this.Kremsorte = Kremsorte;
    }

    @Override
    public String getKremsorte() {
        return Kremsorte;
    }


}
