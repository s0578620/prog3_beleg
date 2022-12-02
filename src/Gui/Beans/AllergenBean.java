package Gui.Beans;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;
import vertrag.Allergen;

public class AllergenBean {

    private Allergen allergen;
    private StringProperty allergenString;

    public AllergenBean(Allergen allergen){
        this.allergen = allergen;
        this.allergenString = new SimpleStringProperty(allergen.toString());

    }

    public static Callback<AllergenBean, Observable[]> extractor() {
        return (AllergenBean a) -> new Observable[]{
                a.allergenString,
        };
    }

    public StringProperty allergenStringProperty() {
        return allergenString;
    }
}
