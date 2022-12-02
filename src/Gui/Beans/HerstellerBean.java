package Gui.Beans;

import GL.Hersteller;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class HerstellerBean {

    private Hersteller hersteller;
    private StringProperty name;


    public HerstellerBean(Hersteller hersteller){
        this.hersteller = hersteller;
        this.name = new SimpleStringProperty(hersteller.getName());
    }

    public static Callback<HerstellerBean, Observable[]> extractor() {
        return (HerstellerBean h) -> new Observable[]{
                h.nameProperty(),
        };
    }

    public StringProperty nameProperty() {
        return name;
    }
}
