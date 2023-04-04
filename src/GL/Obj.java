package GL;
import vertrag.*;
import vertrag.Hersteller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Obj implements Kuchen,Verkaufsobjekt, Serializable {

    private final String kuchentyp;
    private final Hersteller hersteller;
    private final String herstellerString;
    private final Collection<Allergen> allergene;
    private final int naehrwert;
    private final int haltbarkeit;
    private final BigDecimal preis;
    private Date inspektionsdatum;
    private Date insertDate;
    private int fachnummer;

    public Obj(String kuchentyp, Hersteller Hersteller, BigDecimal preis, int naehrwert, int haltbarkeit, Date inspektionsdatum, Collection<Allergen> allergene, Date insertDate){
        this.kuchentyp = kuchentyp;
        this.hersteller = Hersteller;
        this.allergene = allergene;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.preis = preis;
        this.inspektionsdatum = inspektionsdatum;
        this.insertDate = insertDate;
        this.herstellerString = Hersteller.getName();
    }


    public String getKuchentyp() {
        return kuchentyp;
    }

    @Override
    public Hersteller getHersteller() {
        return hersteller;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return allergene;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }

    @Override
    public int getHaltbarkeit() {
        return haltbarkeit;
    }
    public Duration getHaltbarkeitDuration() {
        Date actualDate = new Date();

        Duration tmp = Duration.ofMinutes(Duration.between(insertDate.toInstant(),actualDate.toInstant()).toMinutes());
        int res = (int) tmp.toMinutes();
        int test = haltbarkeit - res;
        Duration tryhard = Duration.ofMinutes(test);
        // this.haltbarkeitActual = (int) tryhard.toMinutes();
        return tryhard;

        /*
            int Haltbarkeit -> 36
            Duration -> 36 minuten definieren

            Result Duration -> Duration - (Duration between actual and insetdate)
         */
    }

    @Override
    public BigDecimal getPreis() {
        return preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return inspektionsdatum;
    }

    public void setInspektionsdatum(Date inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }

    @Override
    public int getFachnummer() {
        return fachnummer;
    }

    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    public Date getInsertDate() {
        return insertDate;
    }
}