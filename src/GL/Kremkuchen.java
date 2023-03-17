package GL;

import vertrag.Allergen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Kremkuchen extends Obj implements vertrag.Kremkuchen,Serializable {

    private String kremsorte;

    public Kremkuchen(String kuchentyp,Hersteller hersteller, BigDecimal preis, int naehrwert, int haltbarkeit, Date inspektionsdatum, Collection<Allergen> allergene, String kremsorte, Date insertDate) {
        super(kuchentyp,hersteller, preis, naehrwert, haltbarkeit, inspektionsdatum, allergene, insertDate);
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return kremsorte;
    }


}
