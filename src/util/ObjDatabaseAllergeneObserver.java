package util;

import GL.ObjDatabase;

import java.util.Observable;
import java.util.Observer;

public class ObjDatabaseAllergeneObserver implements Observer {

    private ObjDatabase oDB;

    private int counter;

    public ObjDatabaseAllergeneObserver(ObjDatabase oDB){
        this.oDB = oDB;
        this.counter = oDB.getAllergenList().size();

    }
    @Override
    public void update(Observable o, Object arg) {
        if(oDB.getAllergenList().size() != counter){
            this.counter = oDB.getAllergenList().size();
            System.out.println("Allergene changed");
        }
    }
}
