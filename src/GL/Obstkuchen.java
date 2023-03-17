package GL;

import vertrag.Allergen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Obstkuchen extends Obj implements vertrag.Obstkuchen {
    private final String obstsorte;

    public Obstkuchen(String kuchentyp, Hersteller hersteller, BigDecimal preis, int naehrwert, int haltbarkeit, Date inspektionsdatum, Collection<Allergen> allergene, String obstsorte, Date insertDate){
        super(kuchentyp,hersteller, preis, naehrwert, haltbarkeit, inspektionsdatum, allergene, insertDate);
        this.obstsorte = obstsorte;
    }

    @Override
    public String getObstsorte() {
        return obstsorte;
    }
}
