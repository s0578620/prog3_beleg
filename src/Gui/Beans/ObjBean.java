package Gui.Beans;

import GL.Obj;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class ObjBean {

    private Obj obj;
    private StringProperty kuchentyp;
    private IntegerProperty fach;
    private StringProperty hersteller;
    private StringProperty inspDate;
    private StringProperty haltbarkeit;


    public ObjBean(Obj obj){
        this.obj = obj;
        this.kuchentyp = new SimpleStringProperty(obj.getKuchentyp());
        this.fach = new SimpleIntegerProperty(obj.getFachnummer());
        this.hersteller = new SimpleStringProperty(obj.getHersteller().getName());
        this.inspDate = new SimpleStringProperty(obj.getInspektionsdatum().toString());
        this.haltbarkeit = new SimpleStringProperty(obj.getHaltbarkeit().toString());
    }

    public static Callback<ObjBean, Observable[]> extractor() {
        return (ObjBean o) -> new Observable[]{
                o.kuchentypProperty(),
                o.fachProperty(),
                o.herstellerProperty(),
                o.inspDateProperty(),
                o.haltbarkeitProperty()
        };
    }

    public StringProperty kuchentypProperty() {
        return kuchentyp;
    }

    public IntegerProperty fachProperty() {
        return fach;
    }

    public StringProperty herstellerProperty() {
        return hersteller;
    }

    public StringProperty inspDateProperty() {
        return inspDate;
    }

    public StringProperty haltbarkeitProperty() {
        return haltbarkeit;
    }

}
