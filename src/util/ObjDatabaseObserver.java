package util;
import GL.ObjDatabase;

import java.util.Observable;
import java.util.Observer;


public class ObjDatabaseObserver implements Observer {

    private ObjDatabase oDB;

    public ObjDatabaseObserver(ObjDatabase oDB){
        this.oDB = oDB;
    }


    @Override
    public void update(Observable o, Object arg) {
        if((oDB.getCapacityMax()*0.9) <= oDB.getObjList().size()){
            System.out.println("90% capacity reached");
        }
    }
}
