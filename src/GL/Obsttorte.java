package GL;

import vertrag.Allergen;
import vertrag.Hersteller;
import vertrag.Kremkuchen;
import vertrag.Obstkuchen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Obsttorte extends obj implements Obstkuchen, Kremkuchen {

    private final String obstsorte;
    private final String kremsorte;


    public Obsttorte(String Kuchentyp, Hersteller Hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Date Inspektionsdatum, Collection<Allergen> Allergens, String obstsorte, String kremsorte, Date insertDate) {
        super(Kuchentyp, Hersteller, Preis, Naehrwert, Haltbarkeit, Inspektionsdatum, Allergens, insertDate);
        this.obstsorte = obstsorte;
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return kremsorte;
    }

    @Override
    public String getObstsorte() {
        return obstsorte;
    }
}
