package util;

import GL.ObjDatabase;

public class ObjDatabaseObserverSimOne implements Observer{

    private ObjDatabase oDB;
    private int counterHersteller = 0;
    private int counterObj = 0;

    public ObjDatabaseObserverSimOne(ObjDatabase oDB){
        this.oDB = oDB;
    }
    @Override
    public void update() {

        if(oDB.getHerstellerList().size() != counterHersteller){
            this.counterHersteller = oDB.getHerstellerList().size();
            System.out.println("Hersteller gesamt: " + counterHersteller);
        }
        if(oDB.getObjList().size() != counterObj){
            this.counterObj = oDB.getObjList().size();
            System.out.println("Kuchen gesamt: " + counterObj);
        }
    }
}
