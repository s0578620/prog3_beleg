package GL;

import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Obstkuchen extends Obj implements vertrag.Obstkuchen {
    private final String obstsorte;

    public Obstkuchen(String Kuchentyp, vertrag.Hersteller Hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Date Inspektionsdatum, Collection<Allergen> Allergens, String Obstsorte, Date insertDate){
        super(Kuchentyp,Hersteller, Preis, Naehrwert, Haltbarkeit, Inspektionsdatum, Allergens, insertDate);
        this.obstsorte = Obstsorte;
    }

    @Override
    public String getObstsorte() {
        return obstsorte;
    }
}
