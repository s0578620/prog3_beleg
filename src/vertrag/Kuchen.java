package vertrag;

import java.time.Duration;
import java.util.Collection;

public interface Kuchen {
    Hersteller getHersteller();
    Collection<Allergen> getAllergene();
    int getNaehrwert();
    Duration getHaltbarkeit();
}
