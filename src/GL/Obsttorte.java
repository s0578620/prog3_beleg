package GL;

import vertrag.Allergen;
import vertrag.Hersteller;
import vertrag.Kremkuchen;
import vertrag.Obstkuchen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Obsttorte extends Obj implements Obstkuchen, Kremkuchen {

    private final String obstsorte;
    private String kremsorte;


    public Obsttorte(String kuchentyp, Hersteller hersteller, BigDecimal preis, int naehrwert, int haltbarkeit, Date inspektionsdatum, Collection<Allergen> allergene, String obstsorte, String kremsorte, Date insertDate) {
        super(kuchentyp, hersteller, preis, naehrwert, haltbarkeit, inspektionsdatum, allergene, insertDate);
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
