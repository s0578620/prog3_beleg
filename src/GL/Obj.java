package GL;
import vertrag.*;
import vertrag.Hersteller;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Obj implements Kuchen,Verkaufsobjekt {

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

    public Obj(String Kuchentyp, Hersteller Hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Date Inspektionsdatum, Collection<Allergen> Allergens, Date insertDate){
        this.kuchentyp = Kuchentyp;
        this.hersteller = Hersteller;
        this.allergene = Allergens;
        this.naehrwert = Naehrwert;
        this.haltbarkeit = Haltbarkeit;
        this.preis = Preis;
        this.inspektionsdatum = Inspektionsdatum;
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
    public Duration getHaltbarkeit() {
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

    public String getHerstellerString() {
        return herstellerString;
    }
}






