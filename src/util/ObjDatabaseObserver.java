package util;
import GL.ObjDatabase;


public class ObjDatabaseObserver implements Observer {

    private ObjDatabase oDB;

    public ObjDatabaseObserver(ObjDatabase oDB){
        this.oDB = oDB;
    }

    @Override
    public void update() {

        if((oDB.getCapacityMax()*0.9) <= oDB.getObjList().size()){
            System.out.println("90% capacity reached");
        }
    }
}
