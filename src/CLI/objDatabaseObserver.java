package CLI;

import GL.objDatabase;
import util.Observer;

public class objDatabaseObserver implements Observer {

    private objDatabase oDB;

    private int counterHersteller = 0;
    private int counterObj = 0;
    public objDatabaseObserver(objDatabase oDB){
        this.oDB = oDB;
    }


    // TODO split oberserver / sim and gl etc
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
        if((oDB.getCapacityMax()*0.9) <= oDB.getObjList().size()){
            System.out.println("90% capacity reached");
        }
    }
}
