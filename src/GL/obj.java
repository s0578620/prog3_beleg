package GL;
import vertrag.*;
import vertrag.Hersteller;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class obj implements Kuchen,Verkaufsobjekt {

    private final String kuchentyp;
    private final Hersteller hersteller;
    private final Collection<Allergen> allergene;
    private final int naehrwert;
    private final int haltbarkeit;
    private final BigDecimal preis;
    private Date inspektionsdatum;
    private Date insertDate;
    private int fachnummer;

    public obj(String Kuchentyp,Hersteller Hersteller, BigDecimal Preis, int Naehrwert, int Haltbarkeit, Date Inspektionsdatum, Collection<Allergen> Allergens, Date insertDate){
        this.kuchentyp = Kuchentyp;
        this.hersteller = Hersteller;
        this.allergene = Allergens;
        this.naehrwert = Naehrwert;
        this.haltbarkeit = Haltbarkeit;
        this.preis = Preis;
        this.inspektionsdatum = Inspektionsdatum;
        this.insertDate = insertDate;

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
        Duration d = Duration.between(insertDate.toInstant(), actualDate.toInstant());
        //System.out.println(haltbarkeit-(d.toMinutes()));
        return d;
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






