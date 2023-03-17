package vertrag;

import java.util.Collection;

public interface Kuchen {
    Hersteller getHersteller();
    Collection<Allergen> getAllergene();
    int getNaehrwert();

    //changed from Duration to Integer
    int getHaltbarkeit();
}
