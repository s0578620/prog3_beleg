package GL;

import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Kremkuchen extends obj implements vertrag.Kremkuchen {
    private final String kremsorte;

    public Kremkuchen(String Kuchentyp,vertrag.Hersteller Hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Date Inspektionsdatum, Collection<Allergen> Allergens, String Kremsorte, Date insertDate) {
        super(Kuchentyp,Hersteller, Preis, Naehrwert, Haltbarkeit, Inspektionsdatum, Allergens, insertDate);
        this.kremsorte = Kremsorte;
    }

    @Override
    public String getKremsorte() {
        return kremsorte;
    }
}
