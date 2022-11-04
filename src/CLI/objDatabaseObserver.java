package CLI;

import GL.objDatabase;

public class objDatabaseObserver implements Observer {

    private objDatabase oDB;


    public objDatabaseObserver(objDatabase oDB){
        this.oDB = oDB;
    }

    @Override
    public void update(int counter) {
        if((oDB.getCapacityMax()*0.9) == counter){
            System.out.println("90% capacity reached");
        }
    }
}
