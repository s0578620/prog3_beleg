package util;

import GL.objDatabase;
import util.Observer;

public class objDatabaseObserver implements Observer {

    private objDatabase oDB;

    public objDatabaseObserver(objDatabase oDB){
        this.oDB = oDB;
    }

    @Override
    public void update() {

        if((oDB.getCapacityMax()*0.9) <= oDB.getObjList().size()){
            System.out.println("90% capacity reached");
        }
    }
}
